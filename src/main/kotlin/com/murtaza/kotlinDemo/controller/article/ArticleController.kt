package com.murtaza.kotlinDemo.controller.article

import com.murtaza.kotlinDemo.model.Article
import com.murtaza.kotlinDemo.model.ArticleRequest
import com.murtaza.kotlinDemo.model.ArticleResponse
import com.murtaza.kotlinDemo.model.User
import com.murtaza.kotlinDemo.repository.UserJPARepository
import com.murtaza.kotlinDemo.service.ArticleService
import com.murtaza.kotlinDemo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*


@RestController
@RequestMapping("/api/article")
class ArticleController (private val articleService: ArticleService,private val userService: UserService, val userJPARepository: UserJPARepository){

    @GetMapping
    fun listAll():Collection<ArticleResponse> =
        articleService.getAllArticles()
        .map { it.toResponse() }


    @PostMapping
    fun addArticle(@RequestBody articleRequest:ArticleRequest):User{
        val foundUser = userService.findByEmail(articleRequest.author)
        if (foundUser !=null) {
            val article=Article(id = UUID.randomUUID(), title = articleRequest.title, content = articleRequest.content, author = foundUser)
            foundUser.articles?.add(article)?:mutableListOf(article)
            return userJPARepository.save(foundUser)
        }
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Articles Not Found")
    }

    @GetMapping("/author/{author}")
    fun getArticlesByAuthor(@PathVariable author:String):List<Article>{
        val user=userService.findByEmail(author)
        return user?.let { articleService.getArticlesByAuthor(it)}?:throw ResponseStatusException(HttpStatus.NOT_FOUND,"Articles Not Found")
    }

    private fun Article.toResponse():ArticleResponse=
        ArticleResponse(id=this.id,
        title=this.title,
        content=this.content)


}

