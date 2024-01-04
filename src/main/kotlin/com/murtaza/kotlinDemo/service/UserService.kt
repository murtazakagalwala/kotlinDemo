package com.murtaza.kotlinDemo.service

import com.murtaza.kotlinDemo.model.User
import com.murtaza.kotlinDemo.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {

    fun createUser(user:User): User?{
        val found=userRepository.findByEmail(user.email)
        if (found != null) {
            found.password=user.password
            found.articles=user.articles
            found.role=user.role
            userRepository.save(found)
        }
        userRepository.save(user)
        return user
    }

    fun findByUUID(uuid: UUID):User? = userRepository.findByUUID(uuid)

    fun findByEmail(email: String):User? = userRepository.findByEmail(email)

    fun findAll():List<User> = userRepository.findAll()

    fun deleteByUUID(uuid: UUID):Boolean = userRepository.deleteByUUID(uuid)

}