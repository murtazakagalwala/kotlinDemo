package com.murtaza.kotlinDemo.controller.article

import com.murtaza.kotlinDemo.model.Article
import com.murtaza.kotlinDemo.model.ArticleResponse
import com.murtaza.kotlinDemo.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/article")
class ArticleController (private val articleService: ArticleService){

    @GetMapping
    fun listAll():Collection<ArticleResponse> =
        articleService.getAllArticles()
        .map { it.toResponse() }


    private fun Article.toResponse():ArticleResponse=
        ArticleResponse(id=this.id,
        title=this.title,
        content=this.content)

}