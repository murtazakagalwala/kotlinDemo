package com.murtaza.kotlinDemo.service

import com.murtaza.kotlinDemo.model.User
import com.murtaza.kotlinDemo.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userRepository: UserRepository):UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByEmail(username)
            ?.toUserDetails()
            ?:throw UsernameNotFoundException("User Not Found")

    }

    private fun User.toUserDetails():UserDetails = org.springframework.security.core.userdetails.User.builder()
        .username(this.email)
        .password(this.password)
        .roles(this.role.name)
        .build()
}