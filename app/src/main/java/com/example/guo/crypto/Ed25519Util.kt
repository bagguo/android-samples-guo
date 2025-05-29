package com.example.guo.crypto

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.util.Base64

/**
 * RSA（Rivest–Shamir–Adleman）
 * 是最早的公钥密码系统之一，被广泛用于安全数据传输。它的安全性取决于整数分解，因此永远不需要安全的RNG（随机数生成器）。与DSA相比，RSA的签名验证速度更快，但生成速度较慢。
 *
 * DSA（数字签名算法）
 * 是用于数字签名的联邦信息处理标准。它的安全性取决于离散的对数问题。与RSA相比，DSA的签名生成速度更快，但验证速度较慢。如果使用错误的数字生成器，可能会破坏安全性。
 *
 * ECDSA（椭圆曲线数字签名算法）
 * 是DSA（数字签名算法）的椭圆曲线实现。椭圆曲线密码术能够以较小的密钥提供与RSA相对相同的安全级别。它还具有DSA对不良RNG敏感的缺点。
 *
 * EdDSA（爱德华兹曲线数字签名算法）
 * 是一种使用基于扭曲爱德华兹曲线的Schnorr签名变体的数字签名方案。签名创建在EdDSA中是确定性的，其安全性是基于某些离散对数问题的难处理性，因此它比DSA和ECDSA更安全，后者要求每个签名都具有高质量的随机性。
 */
object Ed25519Util {
    /**
     * Ed25519是EdDSA签名方案，但使用SHA-512 / 256和Curve25519；它是一条安全的椭圆形曲线，比DSA，ECDSA和EdDSA 提供更好的安全性，并且具有更好的性能（人为注意）。
     */
    private const val CRYPTO_ALGORITHM = "Ed25519"

    fun generateKeyPair(): KeyPair {
        val generator = KeyPairGenerator.getInstance(CRYPTO_ALGORITHM)
        return generator.generateKeyPair()
    }

    fun sign(privateKey: PrivateKey, data: ByteArray): ByteArray {
        val signature = Signature.getInstance(CRYPTO_ALGORITHM)
        signature.initSign(privateKey)
        signature.update(data)
        return signature.sign().apply {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val encodedMsg: String = Base64.getEncoder().encodeToString(this)
            println("sign result to Base64: $encodedMsg")
//            }
        }
    }

    /**
     * 验签
     */
    fun verify(publicKey: PublicKey, data: ByteArray, signedBytes: ByteArray): Boolean {
        val signDecode = Signature.getInstance(CRYPTO_ALGORITHM)
        signDecode.initVerify(publicKey)
        signDecode.update(data)
        return signDecode.verify(signedBytes).apply {
            println("verify result: $this")
        }
    }

}

// test
fun main() {
    val data = ""

    val keyPair = Ed25519Util.generateKeyPair()
    val signed = Ed25519Util.sign(keyPair.private, data.toByteArray())
    Ed25519Util.verify(keyPair.public, data.toByteArray(), signed)
}

