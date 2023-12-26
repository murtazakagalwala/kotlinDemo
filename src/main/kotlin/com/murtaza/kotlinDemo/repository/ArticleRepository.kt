package com.murtaza.kotlinDemo.repository

import com.murtaza.kotlinDemo.model.Article
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ArticleRepository {

    private val articles= listOf<Article>(
        Article(UUID.randomUUID(),"Title 1","Content 1"),
        Article(UUID.randomUUID(),"Title 2","Content 2"))

    fun findAll():Collection<Article> = articles
}

