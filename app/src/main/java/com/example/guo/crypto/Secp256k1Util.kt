package com.example.guo.crypto

import com.example.guo.ext.toHexString
import org.bouncycastle.jce.ECNamedCurveTable
import org.bouncycastle.jce.interfaces.ECPrivateKey
import org.bouncycastle.jce.interfaces.ECPublicKey
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.jce.spec.ECParameterSpec
import org.bouncycastle.jce.spec.ECPrivateKeySpec
import org.bouncycastle.jce.spec.ECPublicKeySpec
import org.bouncycastle.math.ec.ECPoint
import org.jetbrains.annotations.TestOnly
import java.math.BigInteger
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.Security
import java.security.Signature

/**
 * Secp256k1工具类
 * 使用bouncycastle库的secp256k1实现, 签名结果长度140位，不确定是不是Bitcoin DER格式
 *
 * Bitcoin Secp256k1签名结果格式:
 * 140位DER格式
 *
 * Ethereum Secp256k1签名结果格式:
 * 130位，r + s + v
 */
object Secp256k1Util {

    private const val TAG = "Secp256k1Util"

    private const val BOUNCY_CASTLE = "BC"
    private const val CRYPTO_ALGORITHM = "ECDSA" // 椭圆曲线数字签名算法，在数学上表现为一条椭圆曲线
    private const val CURVE_SECP256K1 = "secp256k1" // ECDSA的一种实现，其他还有如：secp256r1
    private const val SHA256_WITH_ECDSA = "SHA256withECDSA"

    init {
        Security.addProvider(BouncyCastleProvider())
    }

    /**
     * 生成 secp256k1 密钥对
     */
    fun generateKeyPair(): KeyPair {
        val keyGen = KeyPairGenerator.getInstance("EC", BOUNCY_CASTLE)
        val ecSpec = ECNamedCurveTable.getParameterSpec(CURVE_SECP256K1)
        keyGen.initialize(ecSpec, SecureRandom())
        val keyPair = keyGen.generateKeyPair()

        val privateKey: ECPrivateKey = keyPair.private as ECPrivateKey
        val publicKey: ECPublicKey = keyPair.public as ECPublicKey

        return keyPair
    }

    /**
     * 签名输出长度为140
     */
    fun sign140(data: ByteArray?, privateKey: PrivateKey?): ByteArray {
        val signature = Signature.getInstance(SHA256_WITH_ECDSA, BOUNCY_CASTLE)
        signature.initSign(privateKey)
        signature.update(data)
        return signature.sign()
    }

    fun verify140(data: ByteArray?, signatureBytes: ByteArray?, publicKey: PublicKey?): Boolean {
        val signature = Signature.getInstance(SHA256_WITH_ECDSA, BOUNCY_CASTLE)
        signature.initVerify(publicKey)
        signature.update(data)
        return signature.verify(signatureBytes)
    }

    /**
     * 根据私钥推导出公钥
     */
    fun derivePublicKeyFromPrivateKey(privateKeyBytes: ByteArray?): PublicKey? {
        val ecSpec: ECParameterSpec = ECNamedCurveTable.getParameterSpec(CURVE_SECP256K1)
        val keyFactory = KeyFactory.getInstance(CRYPTO_ALGORITHM, BOUNCY_CASTLE)

        val d = BigInteger(1, privateKeyBytes)
        val q: ECPoint = ecSpec.g.multiply(d).normalize() // 生成公钥点

        return keyFactory.generatePublic(ECPublicKeySpec(q, ecSpec))
    }

    /**
     * ByteArray privateKey -> PrivateKey
     */
    fun byte2PrivateKey(privateKeyBytes: ByteArray?): PrivateKey? {
        val ecSpec: ECParameterSpec = ECNamedCurveTable.getParameterSpec(CURVE_SECP256K1)
        val keyFactory = KeyFactory.getInstance(CRYPTO_ALGORITHM, BOUNCY_CASTLE)

        val d = BigInteger(1, privateKeyBytes)
        return keyFactory.generatePrivate(ECPrivateKeySpec(d, ecSpec))
    }
}

@TestOnly
fun main() {
    // test sign140
    val data = "".toByteArray()

    val keyPair = Secp256k1Util.generateKeyPair()
    val sign = Secp256k1Util.sign140(data, keyPair.private as ECPrivateKey)
    println("Secp256k1 sign: ${sign.toHexString()}")

    val result = Secp256k1Util.verify140(data, sign, keyPair.public)
    println("Secp256k1 verify: $result")
}