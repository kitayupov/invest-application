package ru.devkit.service.crypt

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * @author k.i.tayupov
 */

private const val SECRET_KEY = "aesEncryptionKey"
private const val INIT_VECTOR = "encryptionIntVec"

object Crypt {

    fun encrypt(value: String): String? {
        try {
            val iv = IvParameterSpec(INIT_VECTOR.toByteArray(charset("UTF-8")))
            val secretKeySpec = SecretKeySpec(SECRET_KEY.toByteArray(charset("UTF-8")), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv)
            val encrypted = cipher.doFinal(value.toByteArray())
            return Base64.encodeToString(encrypted, Base64.DEFAULT)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    fun decrypt(value: String?): String? {
        try {
            val iv = IvParameterSpec(INIT_VECTOR.toByteArray(charset("UTF-8")))
            val secretKeySpec = SecretKeySpec(SECRET_KEY.toByteArray(charset("UTF-8")), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv)
            val original = cipher.doFinal(Base64.decode(value, Base64.DEFAULT))
            return String(original)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }
}
