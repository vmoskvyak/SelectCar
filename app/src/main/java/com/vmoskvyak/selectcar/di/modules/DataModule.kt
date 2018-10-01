package com.vmoskvyak.selectcar.di.modules

import android.arch.lifecycle.ViewModel
import com.vmoskvyak.selectcar.di.scopes.AppScope
import com.vmoskvyak.selectcar.repository.CarsRepository
import com.vmoskvyak.selectcar.repository.CarsRepositoryImpl
import com.vmoskvyak.selectcar.viewmodel.BuiltDatesViewModel
import com.vmoskvyak.selectcar.viewmodel.MainTypesViewModel
import com.vmoskvyak.selectcar.viewmodel.ManufacturersViewModel
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @AppScope
    @Provides
    fun provideCarsRepository(carsRepository: CarsRepositoryImpl) : CarsRepository {
        return carsRepository
    }

    @AppScope
    @Provides
    fun provideManufacturersViewModel(viewModel: ManufacturersViewModel) : ViewModel {
        return viewModel
    }

    @AppScope
    @Provides
    fun provideMainTypesViewModel(viewModel: MainTypesViewModel) : ViewModel {
        return viewModel
    }

    @AppScope
    @Provides
    fun provideBuiltDatesViewModel(viewModel: BuiltDatesViewModel) : ViewModel {
        return viewModel
    }

}
