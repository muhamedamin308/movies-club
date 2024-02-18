package com.example.androidpractice1

data class Users(
    val email: String, val password: Int
)

object ExistedUsers {
    private val users = mutableMapOf<String, Users>()
    init {
        users["Test"] = Users("Test", 1234)
    }

    fun addUser(email: String, user: Users) {
        users[email] = user
    }

    fun getUsers(email: String): Users? = users[email]
    fun deleteUser(email: String) {
        users.remove(email)
    }
}
object Constants {
    const val TAG = "TAG!!!"
}
