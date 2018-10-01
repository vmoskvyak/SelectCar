package com.vmoskvyak.selectcar.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.ui.MainActivity
import com.vmoskvyak.selectcar.ui.adapters.VehiclesAdapter
import com.vmoskvyak.selectcar.viewmodel.BuiltDatesViewModel
import javax.inject.Inject

class BuiltDatesFragment : BaseFragment() {

    @Inject
    lateinit var mViewModel: BuiltDatesViewModel

    private lateinit var adapter: VehiclesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       super.onCreateView(inflater, container, savedInstanceState)

        if (arguments == null) return binding.root
        val manufactureId = arguments?.getString(MANUFACTURE_ID_ARG)
        val mainTypeId = arguments?.getString(MAIN_TYPE_ID_ARG)

        adapter = VehiclesAdapter()
        recyclerView.adapter = adapter

        mViewModel.requestStatus.observe(this, Observer<String> {
            (activity as MainActivity).showErrorDialog(it)
        })

        if (manufactureId == null || mainTypeId == null) return binding.root

        mViewModel.loadBuiltDates(manufactureId, mainTypeId).observe(this,
                Observer<PagedList<VehicleData>> {
                    adapter.submitList(it)
                })

        return binding.root
    }

    companion object {
        const val TAG = "BuiltDatesFragment"
        const val MANUFACTURE_ID_ARG = "manufacture.id.arg"
        const val MAIN_TYPE_ID_ARG = "main.type.id.arg"

        fun newInstance(manufactureId: String, mainTypeId: String): BuiltDatesFragment {
            val detailsFragment = BuiltDatesFragment()
            val args = Bundle()

            args.putString(MANUFACTURE_ID_ARG, manufactureId)
            args.putString(MAIN_TYPE_ID_ARG, mainTypeId)
            detailsFragment.arguments = args

            return detailsFragment
        }
    }

}
