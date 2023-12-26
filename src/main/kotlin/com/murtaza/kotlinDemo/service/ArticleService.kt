package com.murtaza.kotlinDemo.service

import com.murtaza.kotlinDemo.repository.ArticleRepository
import com.murtaza.kotlinDemo.model.Article
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleRepository) {

    fun getAllArticles():Collection<Article> = articleRepository.findAll()

}