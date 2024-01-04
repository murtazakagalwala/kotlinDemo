package com.murtaza.kotlinDemo.repository

import com.murtaza.kotlinDemo.model.Article
import com.murtaza.kotlinDemo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticleJPARepository:JpaRepository<Article,UUID> {
    abstract fun findAllByAuthor(author: User): List<Article>
}

