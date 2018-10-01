package com.vmoskvyak.selectcar.di

import android.app.Application
import com.vmoskvyak.selectcar.SelectCarApplication
import com.vmoskvyak.selectcar.di.builder.ActivityBuilder
import com.vmoskvyak.selectcar.di.builder.FragmentBuilder
import com.vmoskvyak.selectcar.di.modules.DataModule
import com.vmoskvyak.selectcar.di.modules.NetworkModule
import com.vmoskvyak.selectcar.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    DataModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: SelectCarApplication)

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}
