package com.murtaza.kotlinDemo

import com.murtaza.kotlinDemo.model.Bank
import com.murtaza.kotlinDemo.service.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hello")
class BankController {

    @Autowired
    val bankService = BankService()

    @GetMapping
    fun helloworld():String ="Hi from spring boot"

    @GetMapping("/getbanks")
    fun getBanks():Collection<Bank>{
       return bankService.getAllBanks()
    }

}