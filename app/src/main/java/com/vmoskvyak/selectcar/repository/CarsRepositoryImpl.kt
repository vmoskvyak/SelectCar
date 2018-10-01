package com.vmoskvyak.selectcar.repository

import com.vmoskvyak.selectcar.BuildConfig
import com.vmoskvyak.selectcar.network.CarsApi
import com.vmoskvyak.selectcar.network.model.VehicleModel
import retrofit2.Response
import javax.inject.Inject

class CarsRepositoryImpl
    @Inject constructor(private val carsApi: CarsApi): CarsRepository {

    override suspend fun getManufactures(page: Int, pageSize: Int) : Response<VehicleModel> {
        val manufacturer = carsApi.getManufacturer(page, pageSize, BuildConfig.KEY)

        return manufacturer.await()
    }

    override suspend fun getMainTypes(manufacturerId: String, page: Int, pageSize: Int):
            Response<VehicleModel> {
        val mainTypes = carsApi.getMainTypes(manufacturerId, page, pageSize, BuildConfig.KEY)

        return mainTypes.await()
    }

    override suspend fun getBuiltDates(manufacturerId: String, mainTypeId: String):
            Response<VehicleModel> {
        val buildDates = carsApi.getBuildDates(manufacturerId, mainTypeId, BuildConfig.KEY)

        return buildDates.await()
    }

}
