package com.vmoskvyak.selectcar.datasource.details

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.repository.CarsRepository
import kotlinx.coroutines.experimental.launch

class GetManufactureDetailsDataSource(
        private var manufacturerId: String,
        private var carsRepository: CarsRepository,
        private var requestStatus: MutableLiveData<String>) :
        PageKeyedDataSource<Int, VehicleData>() {

    private var pageIndex: Int = 0

    override fun loadInitial(params: LoadInitialParams<Int>,
                             callback: LoadInitialCallback<Int, VehicleData>) {
        launch {
            val response = carsRepository.getMainTypes(manufacturerId, pageIndex, PAGE_SIZE)
            val body = response.body()

            if (!response.isSuccessful || body == null) {
                requestStatus.postValue(response.message())
                return@launch
            }

            val result = ArrayList<VehicleData>()

            for (entry in body.mainTypesMap) {
                result.add(VehicleData(entry.key, entry.value))
            }

            callback.onResult(result, 0, ++pageIndex)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, VehicleData>) {
        launch {
            val response = carsRepository.getMainTypes(manufacturerId, params.key, PAGE_SIZE)
            val body = response.body()

            if (!response.isSuccessful || body == null) {
                requestStatus.postValue(response.message())
                return@launch
            }

            val result = ArrayList<VehicleData>()

            for (entry in body.mainTypesMap) {
                result.add(VehicleData(entry.key, entry.value))
            }

            val adjacentPageKey = if (params.key == body.pageSize) null else params.key + 1
            callback.onResult(result, adjacentPageKey)
        }
    }

    override fun loadBefore(params: LoadParams<Int>,
                            callback: LoadCallback<Int, VehicleData>) {

    }

    companion object {
        private const val PAGE_SIZE = 10
    }

}
