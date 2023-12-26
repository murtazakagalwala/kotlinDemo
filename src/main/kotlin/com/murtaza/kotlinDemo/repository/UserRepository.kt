package com.murtaza.kotlinDemo.repository

import com.murtaza.kotlinDemo.model.Role
import com.murtaza.kotlinDemo.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.beans.Encoder
import java.util.*

@Repository
class UserRepository (private val encoder: PasswordEncoder){

    private val users= mutableListOf(
        User(UUID.randomUUID(),"user@gmail.com",encoder.encode("1234"),Role.USER),
        User(UUID.randomUUID(),"admin@gmail.com",encoder.encode("1234"),Role.ADMIN),
        User(UUID.randomUUID(),"user2@gmail.com",encoder.encode("1234"),Role.USER))


    fun save(user:User):Boolean {
        val copyUser=user.copy(password = encoder.encode(user.password))
        return users.add(copyUser)
    }
    fun findByEmail(email:String):User?=users.firstOrNull{it.email==email}
    fun findByUUID(uuid:UUID):User?=users.firstOrNull{it.id==uuid}
    fun findAll():List<User> = users
    fun deleteByUUID(id:UUID):Boolean{
        val foundUser=findByUUID(id)
        return foundUser?.let { users.remove(it)}?:false
    }
}