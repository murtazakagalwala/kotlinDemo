package com.murtaza.kotlinDemo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties("jwt")
data class JwtConfig(val key:String, val accessTokenExpiration:Long, val refreshTokenExpiration:Long)
