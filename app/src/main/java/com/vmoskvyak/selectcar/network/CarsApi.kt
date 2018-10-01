package com.vmoskvyak.selectcar.network

import com.vmoskvyak.selectcar.BuildConfig
import com.vmoskvyak.selectcar.network.model.VehicleModel
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarsApi {

    @GET("v1/car-types/manufacturer")
    fun getManufacturer(@Query("page") page: Int,
                        @Query("pageSize") pageSize: Int,
                        @Query(BuildConfig.KEY_NAME) apiKey: String):
            Deferred<Response<VehicleModel>>

    @GET("v1/car-types/main-types")
    fun getMainTypes(@Query("manufacturer") manufacturer: String,
                     @Query("page") page: Int,
                     @Query("pageSize") pageSize: Int,
                     @Query(BuildConfig.KEY_NAME) apiKey: String):
            Deferred<Response<VehicleModel>>

    @GET("v1/car-types/built-dates")
    fun getBuildDates(@Query("manufacturer") manufacturer: String,
                      @Query("main-type") mainType: String,
                      @Query(BuildConfig.KEY_NAME) apiKey: String):
            Deferred<Response<VehicleModel>>

}
