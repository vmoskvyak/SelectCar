package com.vmoskvyak.selectcar.di.builder

import com.vmoskvyak.selectcar.ui.fragments.details.ManufactureDetailsFragment
import com.vmoskvyak.selectcar.ui.fragments.main.ManufacturersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeCarsListFragment(): ManufacturersFragment

    @ContributesAndroidInjector
    abstract fun contributeCarDetailsFragment(): ManufactureDetailsFragment

}
