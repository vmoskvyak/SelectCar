package com.vmoskvyak.selectcar.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.vmoskvyak.selectcar.datasource.details.GetManufactureDetailsDataSourceFactory
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.repository.CarsRepository
import javax.inject.Inject

class ManufactureDetailsViewModel
@Inject constructor(private var carsRepository: CarsRepository) : ViewModel() {

    val requestStatus: MutableLiveData<String> = MutableLiveData()

    private val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10).build()

    fun loadManufactureDetails(manufacturerId: String): LiveData<PagedList<VehicleData>> {

        val manufactureDetailsFactory = GetManufactureDetailsDataSourceFactory(
                manufacturerId, carsRepository, requestStatus)

        return LivePagedListBuilder(manufactureDetailsFactory, pagedListConfig)
                .build()
    }

}
