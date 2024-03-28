package com.ingecorp.camilo_zavala_herpm13051_s8.utils

object ConverterUtils {
    fun decimalToBinary(decimal: Int): String = Integer.toBinaryString(decimal)

    fun binaryToDecimal(binary: String): Int = Integer.parseInt(binary, 2)

    // Nuevas funciones para suma y resta
    fun sumDecimal(a: Int, b: Int): Int = a + b

    fun sumBinary(a: String, b: String): String {
        val sum = binaryToDecimal(a) + binaryToDecimal(b)
        return decimalToBinary(sum)
    }

    fun subtractDecimal(a: Int, b: Int): Int = a - b

    fun subtractBinary(a: String, b: String): String {
        val difference = binaryToDecimal(a) - binaryToDecimal(b)
        return decimalToBinary(difference)
    }
}
