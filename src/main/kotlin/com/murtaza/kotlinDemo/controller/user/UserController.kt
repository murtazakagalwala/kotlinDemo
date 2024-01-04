package com.murtaza.kotlinDemo.controller.user

import com.murtaza.kotlinDemo.model.Role
import com.murtaza.kotlinDemo.model.User
import com.murtaza.kotlinDemo.repository.UserJPARepository
import com.murtaza.kotlinDemo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.server.ResponseStatusException
import java.net.http.HttpResponse
import java.util.*

@RestController
@RequestMapping("/api/user")
class UserController(private val userService:UserService,private val userJpaRepository:UserJPARepository){

    @GetMapping
    fun getAllUser():List<UserResponse>{
    return userJpaRepository.findAll().map { it.toResponse() }
    }

    @PostMapping
    fun createUser(@RequestBody userRequest:UserRequest):UserResponse{
        return userService.createUser(userRequest.toUser())
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot Create User")
    }

    @DeleteMapping("/{uuid}")
    fun deleteUser(@PathVariable uuid: UUID):ResponseEntity<Boolean>{
        val found = userService.deleteByUUID(uuid)
        return if (found){
            ResponseEntity.noContent().build()
        }else throw ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found")
    }

    @GetMapping("/{uuid}")
    fun getUser(@PathVariable uuid:UUID):UserResponse{
        return userService.findByUUID(uuid)
            ?.toResponse()
            ?:throw ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found")
    }

    @GetMapping("/email/{email}")
    fun getUser(@PathVariable email:String):UserResponse{
        return userService.findByEmail(email)
            ?.toResponse()
            ?:throw ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found")
    }


    fun UserRequest.toUser():User =
        User(id= UUID.randomUUID(),email=this.email,password=this.password, role = Role.USER)

    fun User.toResponse():UserResponse = UserResponse(uuid=this.id,email=this.email)
}