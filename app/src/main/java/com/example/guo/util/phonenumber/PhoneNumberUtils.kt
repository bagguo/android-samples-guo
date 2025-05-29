package com.example.guo.util.phonenumber

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber

class PhoneNumberUtils {

    fun isValidNumber(number: String) {
        val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()
        try {
            val swissNumberProto: PhoneNumber = phoneUtil.parse(number, "CH")

            System.out.println("test: ====" + phoneUtil.isValidNumber(swissNumberProto))
        } catch (e: NumberParseException) {
            System.err.println("NumberParseException was thrown: $e")
        }
    }
}

fun main() {
    val swissNumberStr = "044 668 18 00"
    PhoneNumberUtils().isValidNumber(swissNumberStr)
    PhoneNumberUtils().isValidNumber("1")
    PhoneNumberUtils().isValidNumber("0086" + "swissNumberStr")
    PhoneNumberUtils().isValidNumber("0086" + "12345")
    PhoneNumberUtils().isValidNumber("0086" + "110")
    PhoneNumberUtils().isValidNumber("0086" + "18305917500")
    PhoneNumberUtils().isValidNumber("0086" + "18305917501")
    PhoneNumberUtils().isValidNumber("0086" + "12345678")
    PhoneNumberUtils().isValidNumber("0086" + "123456789")

}