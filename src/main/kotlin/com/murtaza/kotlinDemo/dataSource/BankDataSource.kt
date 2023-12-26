package com.murtaza.kotlinDemo.dataSource

import com.murtaza.kotlinDemo.model.Bank

interface BankDataSource {

    fun retriveBanks():Collection<Bank>
}