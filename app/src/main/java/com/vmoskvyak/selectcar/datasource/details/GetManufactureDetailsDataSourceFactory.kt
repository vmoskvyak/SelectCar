package com.vmoskvyak.selectcar.datasource.details

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.repository.CarsRepository

class GetManufactureDetailsDataSourceFactory(
        private var manufacturerId: String,
        private var carsRepository: CarsRepository,
        private var requestStatus: MutableLiveData<String>) :
        DataSource.Factory<Int, VehicleData>() {

    private val manufacturerDetails: MutableLiveData<GetManufactureDetailsDataSource>
            = MutableLiveData()

    override fun create(): DataSource<Int, VehicleData> {
        val getManufactureDetailsDataSource =
                GetManufactureDetailsDataSource(manufacturerId, carsRepository, requestStatus)
        manufacturerDetails.postValue(getManufactureDetailsDataSource)

        return getManufactureDetailsDataSource
    }

}
