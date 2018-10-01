package com.vmoskvyak.selectcar.di.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.vmoskvyak.selectcar.BuildConfig
import com.vmoskvyak.selectcar.di.scopes.AppScope
import com.vmoskvyak.selectcar.network.CarsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideCarsApi(retrofit: Retrofit): CarsApi {
        return retrofit.create<CarsApi>(CarsApi::class.java)
    }

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BASE)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @AppScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        try {
            var okHttpClient = OkHttpClient()
            okHttpClient = okHttpClient.newBuilder()
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor).build()

            return okHttpClient
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    @Provides
    @AppScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    companion object {
        private const val CONNECT_TIME_OUT: Long = 25
        private const val WRITE_TIME_OUT: Long = 30
        private const val READ_TIME_OUT: Long = 30

    }

}
