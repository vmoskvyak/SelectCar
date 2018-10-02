package com.vmoskvyak.selectcar.ui.fragments.viewmodel

import com.vmoskvyak.selectcar.network.data.VehicleData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VehicleItemViewModelTest {

    private lateinit var vehicleItemViewModel: VehicleItemViewModel

    @Before
    fun setup() {
        vehicleItemViewModel = VehicleItemViewModel(VehicleData("key", "value"))
    }

    @Test
    fun getText() {
        assertEquals("value", vehicleItemViewModel.getText())
    }

    @Test
    fun getDataId() {
        assertEquals("key", vehicleItemViewModel.getDataId())
    }
}
