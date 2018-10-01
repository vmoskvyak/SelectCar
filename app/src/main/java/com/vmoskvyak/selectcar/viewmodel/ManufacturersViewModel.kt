package com.vmoskvyak.selectcar.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.vmoskvyak.selectcar.datasource.manufacturer.GetManufacturerDataSourceFactory
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.repository.CarsRepository
import javax.inject.Inject

class ManufacturersViewModel @Inject
constructor(private var carsRepository: CarsRepository) : ViewModel() {

    val requestStatus: MutableLiveData<String> = MutableLiveData()

    private val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10).build()

    fun loadManufacturers(): LiveData<PagedList<VehicleData>> {

        val manufacturerDataSourceFactory = GetManufacturerDataSourceFactory(
                carsRepository, requestStatus)

        return LivePagedListBuilder(manufacturerDataSourceFactory, pagedListConfig)
                .build()
    }

}
