package com.murtaza.kotlinDemo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name="article")
class Article(@Id @GeneratedValue val id : UUID,@Column val title:String, @Column val content :String, @JsonIgnore @ManyToOne val author:User)
