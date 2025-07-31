package com.example.guo.wallet

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import com.example.guo.App
import com.example.guo.util.FileUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.Utf8String
import org.web3j.crypto.Bip39Wallet
import org.web3j.crypto.CipherException
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Hash
import org.web3j.crypto.MnemonicUtils
import org.web3j.crypto.RawTransaction
import org.web3j.crypto.TransactionEncoder
import org.web3j.crypto.WalletFile
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.ObjectMapperFactory
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthCall
import org.web3j.protocol.core.methods.response.EthGetBalance
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt
import org.web3j.protocol.core.methods.response.EthSendTransaction
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.DefaultGasProvider
import org.web3j.utils.Convert
import org.web3j.utils.Numeric
import java.io.File
import java.io.IOException
import java.math.BigDecimal
import java.math.BigInteger
import java.util.concurrent.ExecutionException

@Suppress("unused")
class ETHWalletHelper {
    /**
     * WalletUtils.generateBip39Wallet(PASSWORD, ETHWalletHelper.PATH)
     * web3j.ethGetBalance //查询余额
     * web3j.ethSendRawTransaction //发送交易，调用合约
     * web3j.ethCall //调用合约的只读方法
     *
     * web3j.ethGasPrice()
     * web3j.ethGetBalance
     * web3j.ethGetTransactionCount 查询nonce
     * web3j.ethCall 请求Contract nonce, 查询某代币余额
     *
     * Secp256k1创建公私钥
     * priKey: 64
     * pubKey: 128位 priKey椭圆曲线算法得到
     * address: 40位 公钥keccak256公钥得到的hash值，取后40位
     */
    private val objectMapper: ObjectMapper = ObjectMapperFactory.getObjectMapper()
    private var mWallet: Bip39Wallet? = null

    //**************创建钱包*****************
    /**
     * Bip39Wallet json文件：
     * {
     * "address": "a6faefea289235d5678bb089e2ea4cdfbbc4da1b",
     * "id": "a074d861-fde4-46c6-9c61-92ebd12c54f2",
     * "version": 3,
     * "crypto": {
     * "cipher": "aes-128-ctr",
     * "cipherparams": {
     * "iv": "c81fe91c4db8ae9540295eb67f986c78"
     * },
     * "ciphertext": "bcc218740012b1215d56e50256ced9e9a33930c4006a492d36f774984663d5cf",
     * "kdf": "scrypt",
     * "kdfparams": {
     * "dklen": 32,
     * "n": 4096,
     * "p": 6,
     * "r": 8,
     * "salt": "8fc94081b875d187692f2e8f5c6e2c223687627db9e0a18bcffb03308d6553ac"
     * },
     * "mac": "bd8c06c184541dd2e2735aec17e752a6366e596b703309c0af605a26db182a08"
     * }
     * }
     */
    fun generateBip39Wallets() {
        try {
            mWallet = WalletUtils.generateBip39Wallet(PASSWORD, PATH)
        } catch (e: CipherException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (mWallet == null) return

        val filename = mWallet!!.filename
        val file = File(PATH.toString() + File.separator + filename)

        var walletFile: WalletFile? = null
        try {
            walletFile = objectMapper.readValue(file, WalletFile::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        checkNotNull(walletFile)
        Log.d(
            TAG, "generateBip39Wallets: ====address:" + walletFile.address
                    + " getMnemonic:" + mWallet!!.mnemonic
                    + " wallet:" + mWallet.toString()
                    + " walletFile:" + walletFile
        )
    }

    val mnemonic: String
        get() {
            if (mWallet == null) return ""

            val mnemonic = mWallet!!.mnemonic
            Log.d(TAG, "getMnemonic: ====$mnemonic")
            return mnemonic
        }

    val privateKey: String
        get() {
            if (mWallet == null) return ""

            val seed = MnemonicUtils.generateSeed(mWallet!!.mnemonic, PASSWORD)
            val credentials = Credentials.create(ECKeyPair.create(Hash.sha256(seed)))
            val privateKey = credentials.ecKeyPair.privateKey.toString()
            val publicKey = credentials.ecKeyPair.publicKey.toString()
            Log.d(TAG, "getPrivateKey: ====:privateKey:$privateKey publicKey:$publicKey")
            return privateKey
        }

    //**************连接钱包*****************
    private lateinit var web3j: Web3j
    private lateinit var mCredentials: Credentials

    fun connectWallet(serverUrl: String?, password: String?, mnemonic: String?): String {
        web3j = Web3j.build(HttpService(serverUrl))

        var web3ClientVersion: Web3ClientVersion? = null
        try {
            web3ClientVersion = web3j.web3ClientVersion().sendAsync().get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        if (web3ClientVersion != null) {
            val clientVersion = web3ClientVersion.web3ClientVersion
            Log.d(TAG, "connectWallet success: ====clientVersion:$clientVersion")
        }

        mCredentials = WalletUtils.loadBip39Credentials(password, mnemonic)

        val address = mCredentials.getAddress()
        Log.d(TAG, "connectWallet: ====address:$address")
        return address
    }

    /**
     * todo throw Exception
     * 获得某个账户余额，大整数类型
     */
    fun getAccountBalance(address: String?): String {
        if (web3j == null) return ""
        if (TextUtils.isEmpty(address)) return ""

        //获取指定钱包的以太币余额
        var ethGetBlance: EthGetBalance? = null
        try {
            ethGetBlance = web3j!!.ethGetBalance(address, DefaultBlockParameterName.LATEST).send()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // 格式转换 WEI(币种单位) --> ETHER
        var balance = ""
        if (ethGetBlance != null) {
            val balance1 = ethGetBlance.balance
            Log.d(TAG, "getAccountBalance: ====:$balance1")
            balance = Convert.fromWei(BigDecimal(balance1), Convert.Unit.ETHER).toPlainString()
        }
        Log.d(TAG, "getAccountBalance: ====balance:$balance")
        return balance
    }


    /**
     * todo
     * 调用合约
     * 需要支付gas的方法
     */
    fun setValue(userAddress: String, method: String?, value: String?) {
        val function = Function(
            method,
            listOf<Type<*>>(Utf8String(value)),
            emptyList()
        )
        val nonce = getNonce(userAddress)
        val encodedFunction = FunctionEncoder.encode(function)

        val gasLimit = BigInteger(GAS_LIMIT)
        val rawTransaction = RawTransaction.createTransaction(
            nonce,
            DefaultGasProvider.GAS_PRICE,
            gasLimit,
            CONTRACT_ADDRESS,
            encodedFunction
        )

        var response: EthSendTransaction? = null
        try {
            response = web3j!!.ethSendRawTransaction(
                Numeric.toHexString(
                    TransactionEncoder.signMessage(
                        rawTransaction,
                        mCredentials
                    )
                )
            )
                .sendAsync()
                .get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        var transactionHash: String? = null
        if (response != null) {
            transactionHash = response.transactionHash
        }
        Log.d(TAG, "store: ====transactionHash:$transactionHash")
    }

    /**
     * 调用合约的只读方法，无需gas
     */
    fun getValue(method: String?): String {
        val function = Function(
            method,
            emptyList(),
            listOf<TypeReference<*>>(object : TypeReference<Utf8String?>() {
            })
        )

        val encodedFunction = FunctionEncoder.encode(function)
        var response: EthCall? = null
        try {
            response = web3j!!.ethCall(
                Transaction.createEthCallTransaction(null, CONTRACT_ADDRESS, encodedFunction),
                DefaultBlockParameterName.LATEST
            )
                .sendAsync().get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        var result = ""
        if (response != null) {
            result = response.result
        }
        Log.d(TAG, "callContract: ====result:$result")

        return result
    }


    /**
     * todo request.sendAsync().get() IOException
     */
    private fun getNonce(userAddress: String): BigInteger {
        var nonce = BigInteger.ONE
        try {
            val request = web3j!!.ethGetTransactionCount(
                userAddress, DefaultBlockParameterName.PENDING
            )
            val ethGetTransactionCount = request.sendAsync().get()
            nonce = ethGetTransactionCount.transactionCount
        } catch (e: Exception) {
            println("" + e)
        }
        return nonce
    }

    fun getTxStatus(hash: String): Int {
        val web3j = Web3j.build(HttpService(RPC_SEPOLIA))
        val sendTransaction: EthGetTransactionReceipt = web3j.ethGetTransactionReceipt(hash).send()

        /**
         * tip:
         *
         * NoSuchElementException – if no value is present
         * 常会抛出异常
         * throw异常后，捕获再次请求tx status
         */
        val result = sendTransaction.transactionReceipt.get()

        val statusQuantity = Numeric.decodeQuantity(result.status)
        Log.d(TAG, "getTxStatus: ====${statusQuantity.toInt()}")

        return statusQuantity.toInt()
    }


    companion object {
        private val TAG: String = ETHWalletHelper::class.java.simpleName

        const val RPC_SEPOLIA: String = "https://rpc.sepolia.org"

        const val MNEMONIC: String =
            "wood table canoe submit fold page dress auto tell biology appear recipe" //my ganache account
        const val PASSWORD: String = "111111"

        const val ACCOUNT1: String = "0x48F6e751464fcf57632bc3dd5E89Fc5142EEdD1c"
        const val CONTRACT_ADDRESS: String = "0x7ACa5Da559a60ba12c1379ad5eADA2C39e7C6644"
        private const val GAS_LIMIT = "3000000"

        @JvmField
        var PATH: File = App.mContext.getDir("guo-wallet", Context.MODE_PRIVATE)

        // Environment.getExternalStorageDirectory().getPath()  // /storage/emulated/0
        @Volatile
        var mInstance: ETHWalletHelper? = null

        @JvmStatic
        fun getInstance(): ETHWalletHelper? {
            if (mInstance == null) {
                synchronized(ETHWalletHelper::class.java) {
                    if (mInstance == null) {
                        mInstance = ETHWalletHelper()
                    }
                }
            }
            return mInstance
        }

        /**
         * json导入钱包
         */
        @JvmStatic
        fun loadJsonCredentials(filePath: String?): String {
            var credentials: Credentials? = null
            try {
                val json = FileUtil.readJsonFile(filePath)
                credentials = WalletUtils.loadJsonCredentials(PASSWORD, json)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: CipherException) {
                e.printStackTrace()
            }

            var ecKeyPair: ECKeyPair? = null
            if (credentials != null) {
                ecKeyPair = credentials.ecKeyPair
            }

            var privateKey: BigInteger? = null
            var publicKey: BigInteger? = null
            if (ecKeyPair != null) {
                privateKey = ecKeyPair.privateKey
                publicKey = ecKeyPair.publicKey
            }

            checkNotNull(credentials)

            val address = credentials.address
            Log.d(
                TAG, "importWalletByKeyStore: ====address:" + address
                        + " privateKey:" + privateKey
                        + " publicKey:" + publicKey
            )
            return address
        }
    }
}
