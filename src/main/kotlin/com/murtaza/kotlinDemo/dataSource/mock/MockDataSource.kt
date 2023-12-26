package com.murtaza.kotlinDemo.dataSource.mock

import com.murtaza.kotlinDemo.dataSource.BankDataSource
import com.murtaza.kotlinDemo.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockDataSource : BankDataSource {

    private val banks = listOf(Bank("1234",1.2,17),
                                Bank("4567",3.1,5),
                                Bank("8910",2.6,12))

    override fun retriveBanks(): Collection<Bank> {
        return banks
    }


}