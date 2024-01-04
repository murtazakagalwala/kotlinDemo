package com.murtaza.kotlinDemo.repository

import com.murtaza.kotlinDemo.model.Role
import com.murtaza.kotlinDemo.model.User
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.beans.Encoder
import java.util.*

@Repository
class UserRepository (private val encoder: PasswordEncoder, private val userJpaRepository:UserJPARepository){

    private val users= mutableListOf(
        User(UUID.randomUUID(),"user@gmail.com",encoder.encode("1234"),Role.USER),
        User(UUID.randomUUID(),"admin@gmail.com",encoder.encode("1234"),Role.ADMIN),
        User(UUID.randomUUID(),"user2@gmail.com",encoder.encode("1234"),Role.USER))


    fun save(user:User):Boolean {
        val usercopy=User(user.id,user.email,encoder.encode(user.password),user.role)
        val savedUser=userJpaRepository.save(usercopy)
        return true
        //val copyUser=user.copy(password = encoder.encode(user.password))
        //return users.add(usercopy)
    }
    fun findByEmail(email:String):User?=userJpaRepository.findByEmail(email).firstOrNull()
    fun findByUUID(uuid:UUID):User?=userJpaRepository.findByIdOrNull(uuid)
    fun findAll():List<User> = userJpaRepository.findAll()
    fun deleteByUUID(id:UUID):Boolean{
        val foundUser=findByUUID(id)
        return if (foundUser !=null) {
            userJpaRepository.deleteById(id)
            true
        } else false
    }
}