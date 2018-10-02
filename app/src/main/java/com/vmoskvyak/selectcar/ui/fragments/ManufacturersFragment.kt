package com.vmoskvyak.selectcar.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.selectcar.R
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.ui.MainActivity
import com.vmoskvyak.selectcar.ui.adapters.VehiclesAdapter
import com.vmoskvyak.selectcar.ui.adapters.listeners.OnManufactureItemClickListener
import com.vmoskvyak.selectcar.viewmodel.ManufacturersViewModel
import javax.inject.Inject

class ManufacturersFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: ManufacturersViewModel

    private lateinit var adapter: VehiclesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(getString(R.string.app_name))

        adapter = VehiclesAdapter()
        recyclerView.adapter = adapter

        initOnManufactureClick()

        viewModel.requestStatus.observe(this, Observer<String> {
            (activity as MainActivity).showErrorDialog(it)
        })

        viewModel.loadManufacturers().observe(this, Observer<PagedList<VehicleData>> {
            adapter.submitList(it)
        })

        return binding.root
    }

    private fun initOnManufactureClick() {
        adapter.manufacturerClickListener = object : OnManufactureItemClickListener {
            override fun onItemClick(manufacturerId: String, manufactureName: String) {
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.
                        setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                fragmentTransaction?.
                        replace(R.id.fl_container,
                                MainTypesFragment.newInstance(manufacturerId, manufactureName),
                                MainTypesFragment.TAG)?.
                        addToBackStack(TAG)?.
                        commit()
            }
        }
    }

    companion object {
        const val TAG = "ManufacturersFragment"
    }

}
