package com.zyh.lib_base.utils

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


/**
 * @author Created by zyh on 2021/12/7.
 * @description
 */
object AESUtils {

    private const val DEFAULT_SECRET_KEY = "JhRwVdH1BamwZpGu"

    private const val AES = "AES"

    private const val VI_LENGTH = 16

    private const val CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding"
    private const val PATTERN = "AES/CBC/NoPadding"
    private const val ANALYTICS_SECRET_KEY = "GRniMFX8qpXytxOd"
    /**
     * AES加密
     */
    fun encode(content: String): String? {
        try {
            val secretKey: SecretKey = SecretKeySpec(DEFAULT_SECRET_KEY.toByteArray(), AES)
            val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
            val random = SecureRandom()
            val iv = ByteArray(VI_LENGTH)
            random.nextBytes(iv)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec(iv))

            val byteEncode = content.toByteArray(StandardCharsets.UTF_8)

            val byteAES = cipher.doFinal(byteEncode)

            val jsonObject = JsonObject().apply {
                addProperty("iv", Base64Utils.encode(iv))
                addProperty("value", Base64Utils.encode(byteAES))
            }

            return Base64Utils.encode(jsonObject.toString().toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * AES解密
     */
    fun decode(content: String?): String? {
        try {
            val content = Base64Utils.decode(content!!)
            val j = JsonParser.parseString(String(content)) as JsonObject

            val secretKey: SecretKey = SecretKeySpec(ANALYTICS_SECRET_KEY.toByteArray(), AES)
            val cipher = Cipher.getInstance(PATTERN)
            cipher.init(Cipher.DECRYPT_MODE,
                secretKey,
                IvParameterSpec(Base64Utils.decode(j.get("iv").asString)))

            val byteContent = Base64Utils.decode(j.get("value").asString)

            val byteDecode = cipher.doFinal(byteContent)
            return String(byteDecode, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
    fun encodeNoPadding(content: String):String?{
        try {
            val secretKey: SecretKey = SecretKeySpec(ANALYTICS_SECRET_KEY.toByteArray(), AES)
            val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
            val random = SecureRandom()
            val iv = getRandomString16()
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec(iv.toByteArray()))

            val byteEncode = content.toByteArray(StandardCharsets.UTF_8)

            val byteAES = cipher.doFinal(byteEncode)

            val jsonObject = JsonObject().apply {
                addProperty("iv", Base64Utils.encode(iv.toByteArray()))
                addProperty("value", Base64Utils.encode(byteAES))
            }

            return Base64Utils.encode(jsonObject.toString().toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
    private fun getRandomString16():String{
        val range = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var sb = StringBuilder()
        var random = Random()
        for (i in 0..15){
            sb.append(range[random.nextInt(62)])
        }
        return sb.toString()
    }
}