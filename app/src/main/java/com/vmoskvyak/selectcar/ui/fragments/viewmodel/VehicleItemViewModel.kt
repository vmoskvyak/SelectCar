package com.vmoskvyak.selectcar.ui.fragments.viewmodel

import com.vmoskvyak.selectcar.network.data.VehicleData

class VehicleItemViewModel(private val vehicleData: VehicleData?) {

    fun getText(): String {
        return vehicleData?.value ?: ""
    }

    fun getDataId() : String? {
        return vehicleData?.key
    }

}
