package com.example.guo.model

data class User(var firstName: String?, var lastName: String?)

//默认参数与具名参数
//data class User(var firstName: String?, var lastName: String? = null)
//
//// usage
//val jane = User ("Jane") // same as User("Jane", null)
//val joe = User ("John", "Doe")

//val john = User (firstName = "John", lastName = "Doe")