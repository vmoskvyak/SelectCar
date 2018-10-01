package com.vmoskvyak.selectcar.datasource.maintypes

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.repository.CarsRepository

class GetMainTypesDataSourceFactory(
        private var manufacturerId: String,
        private var carsRepository: CarsRepository,
        private var requestStatus: MutableLiveData<String>) :
        DataSource.Factory<Int, VehicleData>() {

    private val mManufacturerDetails: MutableLiveData<GetMainTypesDataSource>
            = MutableLiveData()

    override fun create(): DataSource<Int, VehicleData> {
        val getManufactureDetailsDataSource =
                GetMainTypesDataSource(manufacturerId, carsRepository, requestStatus)
        mManufacturerDetails.postValue(getManufactureDetailsDataSource)

        return getManufactureDetailsDataSource
    }

}
