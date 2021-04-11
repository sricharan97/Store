package com.udacity.shoestore.models

import androidx.databinding.InverseMethod

/**
 * Converter object to be used for two way databinding for non-String data in edit text fields(shoe Size)
 */
object Converter {
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(
        value: Double
    ): String {
        return value.toString()
    }

    @JvmStatic
    fun stringToDouble(
        value: String
    ): Double {
        return if (value.isNotEmpty()) {
            value.toDouble()
        } else {
            "0".toDouble()
        }
    }
}