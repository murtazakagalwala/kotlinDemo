package com.murtaza.kotlinDemo.repository

import com.murtaza.kotlinDemo.model.Role
import com.murtaza.kotlinDemo.model.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository {

    private val users= mutableListOf(
        User(UUID.randomUUID(),"user@gmail.com","1234",Role.USER),
        User(UUID.randomUUID(),"admin@gmail.com","1234",Role.ADMIN),
        User(UUID.randomUUID(),"user2@gmail.com","1234",Role.USER))


    fun save(user:User):Boolean = users.add(user)
    fun findByEmail(email:String):User?=users.firstOrNull{it.email==email}
    fun findByUUID(uuid:UUID):User?=users.firstOrNull{it.id==uuid}
    fun findAll():List<User> = users
    fun deleteByUUID(id:UUID):Boolean{
        val foundUser=findByUUID(id)
        return foundUser?.let { users.remove(it)}?:false
    }
}