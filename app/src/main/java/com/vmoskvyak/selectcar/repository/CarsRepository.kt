package com.vmoskvyak.selectcar.repository

import com.vmoskvyak.selectcar.network.model.BuiltDatesModel
import com.vmoskvyak.selectcar.network.model.MainTypesModel
import com.vmoskvyak.selectcar.network.model.ManufacturerModel
import retrofit2.Response

interface CarsRepository {

    suspend fun getManufactures(page: Int, pageSize: Int) : Response<ManufacturerModel>

    suspend fun getMainTypes(manufacturerId: String, page: Int, pageSize: Int) :
            Response<MainTypesModel>

    suspend fun getBuiltDates(manufacturerId: Int, mainTypeId: Int) : Response<BuiltDatesModel>

}
