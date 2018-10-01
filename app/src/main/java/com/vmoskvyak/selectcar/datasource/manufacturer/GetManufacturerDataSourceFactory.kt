package com.vmoskvyak.selectcar.datasource.manufacturer

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.repository.CarsRepository

class GetManufacturerDataSourceFactory(
        private val repository: CarsRepository,
        private var requestStatus: MutableLiveData<String>
) : DataSource.Factory<Int, VehicleData>() {

    private val manufacturerData: MutableLiveData<GetManufacturerDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, VehicleData> {
        val getManufacturerDataSource = GetManufacturerDataSource(repository, requestStatus)
        manufacturerData.postValue(getManufacturerDataSource)

        return getManufacturerDataSource
    }

}
