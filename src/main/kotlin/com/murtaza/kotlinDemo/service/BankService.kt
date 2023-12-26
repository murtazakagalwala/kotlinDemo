package com.murtaza.kotlinDemo.service

import com.murtaza.kotlinDemo.dataSource.mock.MockDataSource
import com.murtaza.kotlinDemo.model.Bank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BankService{

    @Autowired
    val mockDataSource=MockDataSource()
    fun getAllBanks():Collection<Bank>{
        return mockDataSource.retriveBanks()
    }
}