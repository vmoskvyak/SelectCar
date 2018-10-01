package com.vmoskvyak.selectcar.ui.fragments.main

import com.vmoskvyak.selectcar.network.data.VehicleData

class ManufacturerItemViewModel(private val vehicleData: VehicleData?) {

    fun getName(): String {
        return vehicleData?.value ?: ""
    }

    fun getManufacturerId() : String? {
        return vehicleData?.key
    }

}
