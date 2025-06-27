package com.example.guo.language.kotlin.enums

import com.blankj.utilcode.util.GsonUtils
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class User(
    var userType: UserType = UserType.ADMIN,
)

enum class UserType(
    // 指定typeValue序列化
    @get:JsonValue
    val typeValue: Int,
) {
    ADMIN(2),
    USER(4),
    ;

    companion object {
        // 指定creator反序列化
        @JsonCreator
        @JvmStatic
        fun creator(typeValue: Int): UserType {
            val found = UserType.values().find { it.typeValue == typeValue }
            return found ?: throw IllegalArgumentException("not found IconEnum with $typeValue")
        }
    }
}

fun main() {
    val user = User()
    val json = GsonUtils.toJson(user)
    println(json)
}
