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
import com.vmoskvyak.selectcar.ui.adapters.listeners.OnMainTypeItemClickListener
import com.vmoskvyak.selectcar.viewmodel.MainTypesViewModel
import javax.inject.Inject

class MainTypesFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: MainTypesViewModel

    private lateinit var adapter: VehiclesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        if (arguments == null) return binding.root

        val manufactureId = arguments?.getString(MANUFACTURE_ID_ARG)
        (activity as MainActivity).setToolbarTitle(arguments?.getString(MANUFACTURE_NAME_ARG))

        initAdapter(manufactureId)

        viewModel.requestStatus.observe(this, Observer<String> {
            (activity as MainActivity).showErrorDialog(it)
        })

        manufactureId?.let { id ->
            viewModel.loadManufactureDetails(id).observe(this,
                    Observer<PagedList<VehicleData>> {
                adapter.submitList(it)
            })
        }

        return binding.root
    }

    private fun initAdapter(manufactureId: String?) {
        adapter = VehiclesAdapter(manufactureId)
        recyclerView.adapter = adapter
        initOnMainTypeClick()
    }

    private fun initOnMainTypeClick() {
        adapter.mainTypeClickListener = object : OnMainTypeItemClickListener {
            override fun onItemClick(manufactureId: String, mainTypeId: String) {
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.
                        setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                fragmentTransaction?.
                        replace(R.id.fl_container,
                                BuiltDatesFragment.newInstance(manufactureId, mainTypeId),
                                BuiltDatesFragment.TAG)?.
                        addToBackStack(TAG)?.
                        commit()
            }
        }
    }

    companion object {
        const val TAG = "MainTypesFragment"
        private const val MANUFACTURE_ID_ARG = "manufacture.id.arg"
        private const val MANUFACTURE_NAME_ARG = "manufacture.name.arg"

        fun newInstance(manufactureId: String, manufactureName: String): MainTypesFragment {
            val detailsFragment = MainTypesFragment()
            val args = Bundle()

            args.putString(MANUFACTURE_ID_ARG, manufactureId)
            args.putString(MANUFACTURE_NAME_ARG, manufactureName)
            detailsFragment.arguments = args

            return detailsFragment
        }
    }

}
