package com.murtaza.kotlinDemo.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name="application_user")
class User (@Id @GeneratedValue val id :UUID, @Column val email :String, @Column var password: String, @Column @Enumerated(EnumType.STRING) var role:Role, @OneToMany(cascade = arrayOf(CascadeType.ALL),
    mappedBy = "author", orphanRemoval = true)var articles:MutableList<Article>? =null){
}


enum class Role{
    USER,ADMIN
}
