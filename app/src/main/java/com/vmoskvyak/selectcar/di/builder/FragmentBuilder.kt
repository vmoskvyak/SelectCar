package com.vmoskvyak.selectcar.di.builder

import com.vmoskvyak.selectcar.ui.fragments.BuiltDatesFragment
import com.vmoskvyak.selectcar.ui.fragments.MainTypesFragment
import com.vmoskvyak.selectcar.ui.fragments.ManufacturersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeManufacturersFragment(): ManufacturersFragment

    @ContributesAndroidInjector
    abstract fun contributeMainTypesFragment(): MainTypesFragment

    @ContributesAndroidInjector
    abstract fun contribureBuiltDatesFragment(): BuiltDatesFragment

}
