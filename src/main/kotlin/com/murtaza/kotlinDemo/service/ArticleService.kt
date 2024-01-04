package com.murtaza.kotlinDemo.service

import com.murtaza.kotlinDemo.repository.ArticleJPARepository
import com.murtaza.kotlinDemo.model.Article
import com.murtaza.kotlinDemo.model.User
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleJPARepository) {

    fun getAllArticles():Collection<Article> = articleRepository.findAll()

    fun addArticle(article: Article):Article = articleRepository.save(article)

    fun getArticlesByAuthor(author: User): List<Article> {

        return articleRepository.findAllByAuthor(author)

    }
}