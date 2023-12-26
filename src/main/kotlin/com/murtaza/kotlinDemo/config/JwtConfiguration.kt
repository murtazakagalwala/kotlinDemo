package com.murtaza.kotlinDemo.config

import com.murtaza.kotlinDemo.repository.UserRepository
import com.murtaza.kotlinDemo.service.CustomUserDetailsService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties(JwtConfig::class)
class JwtConfiguration {

    @Bean
    fun userDetailsService(userRepository: UserRepository):UserDetailsService{
        return CustomUserDetailsService(userRepository)
    }

    @Bean
    fun encoder():PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(userRepository: UserRepository):AuthenticationProvider{
        return DaoAuthenticationProvider().also {
            it.setPasswordEncoder(encoder())
            it.setUserDetailsService(userDetailsService(userRepository))
        }
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration):AuthenticationManager{
        return config.authenticationManager

    }
}