package com.vmoskvyak.selectcar.network.data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VehicleDataTest {

    private lateinit var vehicleData: VehicleData

    @Before
    fun setup() {
        vehicleData = VehicleData("key", "value")
    }

    @Test
    fun getKey() {
        assertEquals("key", vehicleData.key)
    }

    @Test
    fun setKey() {
        vehicleData.key = "newKey"
        assertEquals("newKey", vehicleData.key)
    }

    @Test
    fun getValue() {
        assertEquals("value", vehicleData.value)
    }

    @Test
    fun setValue() {
        vehicleData.value = "newValue"
        assertEquals("newValue", vehicleData.value)
    }

    @Test
    fun vehicleData() {
        val newVehicleData = VehicleData("key", "value")
        assertEquals(newVehicleData, vehicleData)
    }

}
