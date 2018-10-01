package com.vmoskvyak.selectcar.di.modules

import android.arch.lifecycle.ViewModel
import com.vmoskvyak.selectcar.di.scopes.AppScope
import com.vmoskvyak.selectcar.repository.CarsRepository
import com.vmoskvyak.selectcar.repository.CarsRepositoryImpl
import com.vmoskvyak.selectcar.viewmodel.ManufactureDetailsViewModel
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
    fun provideCarsListViewModel(viewModel: ManufacturersViewModel) : ViewModel {
        return viewModel
    }

    @AppScope
    @Provides
    fun provideCarDetailsViewModel(viewModel: ManufactureDetailsViewModel) : ViewModel {
        return viewModel
    }

}
