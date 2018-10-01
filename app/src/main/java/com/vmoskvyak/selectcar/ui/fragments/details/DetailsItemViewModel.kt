package com.vmoskvyak.selectcar.ui.fragments.details

import com.vmoskvyak.selectcar.network.data.VehicleData

class DetailsItemViewModel(private val vehicleData: VehicleData?) {

    fun getName(): String {
        return vehicleData?.value ?: ""
    }

}
