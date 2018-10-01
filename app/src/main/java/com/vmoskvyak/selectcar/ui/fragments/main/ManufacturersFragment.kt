package com.vmoskvyak.selectcar.ui.fragments.main

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.selectcar.R
import com.vmoskvyak.selectcar.databinding.FragmentCarsListBinding
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.ui.MainActivity
import com.vmoskvyak.selectcar.ui.adapters.ManufacturerAdapter
import com.vmoskvyak.selectcar.ui.adapters.OnItemClickListener
import com.vmoskvyak.selectcar.ui.fragments.BaseFragment
import com.vmoskvyak.selectcar.ui.fragments.details.ManufactureDetailsFragment
import com.vmoskvyak.selectcar.viewmodel.ManufacturersViewModel
import javax.inject.Inject

class ManufacturersFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: ManufacturersViewModel

    private lateinit var adapter: ManufacturerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCarsListBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_cars_list, container, false)

        initRecycleView(binding)

        viewModel.requestStatus.observe(this, Observer<String> {
            (activity as MainActivity).showErrorDialog(it)
        })

        viewModel.loadManufacturers().observe(this, Observer<PagedList<VehicleData>> {
            adapter.submitList(it)
        })

        return binding.root
    }

    private fun initRecycleView(binding: FragmentCarsListBinding) {
        adapter = ManufacturerAdapter()
        initOnManufactureClick()

        val recyclerView = binding.rvManufacturer

        recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
    }

    private fun initOnManufactureClick() {
        adapter.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(manufacturerId: String) {
                fragmentManager?.
                        beginTransaction()?.
                        replace(R.id.fl_container,
                        ManufactureDetailsFragment.newInstance(manufacturerId),
                        ManufactureDetailsFragment.TAG)?.
                        addToBackStack(ManufacturersFragment.TAG)?.
                        commit()
            }

        }
    }

    companion object {
        const val TAG = "CarListFragment"
    }

}
