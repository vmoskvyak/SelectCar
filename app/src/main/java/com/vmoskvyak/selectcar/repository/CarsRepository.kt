package com.vmoskvyak.selectcar.repository

import com.vmoskvyak.selectcar.network.model.VehicleModel
import retrofit2.Response

interface CarsRepository {

    suspend fun getManufactures(page: Int, pageSize: Int) : Response<VehicleModel>

    suspend fun getMainTypes(manufacturerId: String, page: Int, pageSize: Int) :
            Response<VehicleModel>

    suspend fun getBuiltDates(manufacturerId: String, mainTypeId: String) : Response<VehicleModel>

}
