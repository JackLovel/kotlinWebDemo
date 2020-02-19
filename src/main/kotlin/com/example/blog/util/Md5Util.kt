package com.example.blog.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object Md5Util {
    /**
     * MD5加密类
     * @param str 要加密的字符串
     * @return    加密后的字符串
     */
    fun code(str: String): String? {
        try {
            val md = MessageDigest.getInstance("MD5")
            md.update(str.toByteArray())
            val byteDigest = md.digest()
            var i: Int
            val buf = StringBuffer("")
            for (offset in byteDigest.indices) {
                i = byteDigest[offset].toInt()
                if (i < 0) i += 256
                if (i < 16) buf.append("0")
                buf.append(Integer.toHexString(i))
            }
            //32位加密
            return buf.toString()
            // 16位的加密
//return buf.toString().substring(8, 24);
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            return null
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(code("gog"))
    }
}