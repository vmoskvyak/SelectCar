package com.vmoskvyak.selectcar.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.vmoskvyak.selectcar.datasource.builtdates.GetBuiltDatesDataSourceFactory
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.repository.CarsRepository
import javax.inject.Inject

class BuiltDatesViewModel
@Inject constructor(private var carsRepository: CarsRepository) : ViewModel() {

    private val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10).build()

    val requestStatus: MutableLiveData<String> = MutableLiveData()

    fun loadBuiltDates(manufacturerId: String, mainTypeId: String): LiveData<PagedList<VehicleData>> {

        val dataSourceFactory = GetBuiltDatesDataSourceFactory(
                manufacturerId, mainTypeId, carsRepository, requestStatus)

        return LivePagedListBuilder(dataSourceFactory, pagedListConfig)
                .build()
    }

}
