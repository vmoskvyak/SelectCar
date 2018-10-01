package com.vmoskvyak.selectcar.datasource.builtdates

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.repository.CarsRepository
import kotlinx.coroutines.experimental.launch

class GetBuiltDatesDataSource(
        private var manufacturerId: String,
        private var mainTypeId: String,
        private var carsRepository: CarsRepository,
        private var requestStatus: MutableLiveData<String>) :
        PageKeyedDataSource<Int, VehicleData>() {

    override fun loadInitial(params: LoadInitialParams<Int>,
                             callback: LoadInitialCallback<Int, VehicleData>) {
        launch {
            val response = carsRepository.getBuiltDates(manufacturerId, mainTypeId)
            val body = response.body()

            if (!response.isSuccessful || body == null) {
                requestStatus.postValue(response.message())
                return@launch
            }

            val result = ArrayList<VehicleData>()

            for (entry in body.dataMap) {
                result.add(VehicleData(entry.key, entry.value))
            }

            callback.onResult(result, 0, null)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, VehicleData>) {

    }

    override fun loadBefore(params: LoadParams<Int>,
                            callback: LoadCallback<Int, VehicleData>) {

    }

}
