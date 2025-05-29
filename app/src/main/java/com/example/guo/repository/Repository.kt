package com.example.guo.repository

import com.example.guo.model.User

object Repository
// keeping the constructor private to enforce the usage of getInstance
//private constructor()
{
    private val users = mutableListOf<User>()

    val formattedUserNames: List<String?>
        get() {
            return users.map { user ->
                if (user.lastName != null) {
                    if (user.firstName != null) {
                        "${user.firstName} ${user.lastName}"
                    } else {
                        user.lastName ?: "Unknown"
                    }
                } else {
                    user.firstName ?: "Unknown"
                }
            }
        }

    init {
        val user1 = User("Jane", "")
        val user2 = User("John", null)
        val user3 = User("Anne", "Doe")

        users.add(user1)
        users.add(user2)
        users.add(user3)
    }

    fun getUsers(): List<User?>? {
        return users
    }


//    companion object {
//        private var INSTANCE: Repository? = null
//        val instance: Repository?
//            get() {
//                if (INSTANCE == null) {
//                    synchronized(Repository::class.java) {
//                        if (INSTANCE == null) {
//                            INSTANCE = Repository()
//                        }
//                    }
//                }
//                return INSTANCE
//            }
//    }
}