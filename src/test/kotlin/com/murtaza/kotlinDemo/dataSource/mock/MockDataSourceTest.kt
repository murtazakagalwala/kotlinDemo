package com.murtaza.kotlinDemo.dataSource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MockDataSourceTest{

    private val mockDataSource=MockDataSource()

    @Test
    fun testBanksPresent(){

        val banks= mockDataSource.retriveBanks()

        Assertions.assertThat(banks).hasSizeGreaterThan(0)
    }

}