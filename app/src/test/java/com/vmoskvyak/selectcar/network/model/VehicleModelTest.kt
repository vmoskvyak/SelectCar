package com.vmoskvyak.selectcar.network.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VehicleModelTest {

    private lateinit var vehicleModel: VehicleModel

    @Before
    fun setup() {
        vehicleModel = VehicleModel()
        vehicleModel.page = 1
        vehicleModel.pageSize = 15
        vehicleModel.totalPageCount = 4
        vehicleModel.dataMap = mutableMapOf("key1" to "value1", "key2" to "value2",
                "key3" to "value3")
    }

    @Test
    fun getPage() {
        assertEquals(1, vehicleModel.page)
    }

    @Test
    fun setPage() {
        vehicleModel.page = 2
        assertEquals(2, vehicleModel.page)
    }

    @Test
    fun getPageSize() {
        assertEquals(15, vehicleModel.pageSize)
    }

    @Test
    fun setPageSize() {
        vehicleModel.pageSize = 30
        assertEquals(30, vehicleModel.pageSize)
    }

    @Test
    fun getTotalPageCount() {
        assertEquals(4, vehicleModel.totalPageCount)
    }

    @Test
    fun setTotalPageCount() {
        vehicleModel.totalPageCount = 5
        assertEquals(5, vehicleModel.totalPageCount)
    }

    @Test
    fun getDataMap() {
        val mutableMapOf = mutableMapOf("key1" to "value1", "key2" to "value2",
                "key3" to "value3")
        assertEquals(mutableMapOf, vehicleModel.dataMap)
    }

    @Test
    fun setDataMap() {
        val mutableMapOf = mutableMapOf("newKey1" to "newValue1", "newKey2" to "newValue2",
                "newKey3" to "newValue3")

        vehicleModel.dataMap = mutableMapOf("newKey1" to "newValue1", "newKey2" to "newValue2",
                "newKey3" to "newValue3")
        assertEquals(mutableMapOf, vehicleModel.dataMap)
    }

}
