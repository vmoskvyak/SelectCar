package com.vmoskvyak.selectcar.datasource.builtdates

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.repository.CarsRepository

class GetBuiltDatesDataSourceFactory(
        private var manufacturerId: String,
        private var mainTypeId: String,
        private var carsRepository: CarsRepository,
        private var requestStatus: MutableLiveData<String>) :
        DataSource.Factory<Int, VehicleData>() {

    private val builtDates: MutableLiveData<GetBuiltDatesDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, VehicleData> {
        val dataSource =
                GetBuiltDatesDataSource(manufacturerId, mainTypeId, carsRepository, requestStatus)
        builtDates.postValue(dataSource)

        return dataSource
    }

}
