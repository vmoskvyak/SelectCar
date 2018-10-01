package com.vmoskvyak.selectcar.repository

import com.vmoskvyak.selectcar.BuildConfig
import com.vmoskvyak.selectcar.network.CarsApi
import com.vmoskvyak.selectcar.network.model.BuiltDatesModel
import com.vmoskvyak.selectcar.network.model.MainTypesModel
import com.vmoskvyak.selectcar.network.model.ManufacturerModel
import retrofit2.Response
import javax.inject.Inject

class CarsRepositoryImpl
    @Inject constructor(private val carsApi: CarsApi): CarsRepository {

    override suspend fun getManufactures(page: Int, pageSize: Int) : Response<ManufacturerModel> {
        val manufacturer = carsApi.getManufacturer(page, pageSize, BuildConfig.KEY)

        return manufacturer.await()
    }

    override suspend fun getMainTypes(manufacturerId: String, page: Int, pageSize: Int):
            Response<MainTypesModel> {
        val mainTypes = carsApi.getMainTypes(manufacturerId, page, pageSize, BuildConfig.KEY)

        return mainTypes.await()
    }

    override suspend fun getBuiltDates(manufacturerId: Int, mainTypeId: Int):
            Response<BuiltDatesModel> {
        val buildDates = carsApi.getBuildDates(manufacturerId, mainTypeId, BuildConfig.KEY)

        return buildDates.await()
    }

}
