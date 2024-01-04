package com.murtaza.kotlinDemo.service

import com.murtaza.kotlinDemo.config.JwtConfig
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Parser
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date

@Service
class JwtService(private final val jwtConfig: JwtConfig) {

    private val privateKey= Keys.hmacShaKeyFor(jwtConfig.key.toByteArray())

    fun generate(userDetails:UserDetails,
                 expirationDate:Date,
                 additionalClaims:Map<String,Any> = emptyMap()):String{
        return Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .expiration(expirationDate)
            .add(additionalClaims)
            .issuedAt(Date(System.currentTimeMillis()))
            .and()
            .signWith(privateKey)
            .compact()
    }

    fun extractEmail(token: String):String?{
        return getAllClaims(token).subject
    }

    fun isExpired(token:String):Boolean{
        return getAllClaims(token).expiration.before(Date(System.currentTimeMillis()))
    }

    fun isValid(token: String,userDetails: UserDetails):Boolean{
        return extractEmail(token)==userDetails.username && !isExpired(token)
    }
    fun getAllClaims(token:String):Claims{
        val parser=Jwts.parser().verifyWith(privateKey)
        return parser.build().parseSignedClaims(token).payload
    }
}