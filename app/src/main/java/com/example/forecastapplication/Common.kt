package com.example.forecastapplication

class Common {
    companion object {
        fun convertToDate(dt: String): Int {
            val str: List<String> = dt.split(" ")
            val str2: List<String> = str[0].split("-")

            return str2[2].toInt()
        }
    }
}