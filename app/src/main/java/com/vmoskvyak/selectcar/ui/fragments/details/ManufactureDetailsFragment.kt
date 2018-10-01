package com.vmoskvyak.selectcar.ui.fragments.details

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.selectcar.R
import com.vmoskvyak.selectcar.databinding.FragmentCarsDetailsBinding
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.ui.MainActivity
import com.vmoskvyak.selectcar.ui.adapters.DetailsAdapter
import com.vmoskvyak.selectcar.ui.fragments.BaseFragment
import com.vmoskvyak.selectcar.viewmodel.ManufactureDetailsViewModel
import javax.inject.Inject

class ManufactureDetailsFragment: BaseFragment() {

    @Inject
    lateinit var viewModel: ManufactureDetailsViewModel

    private lateinit var adapter: DetailsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCarsDetailsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_cars_details, container, false)

        if (arguments == null) return binding.root

        initRecycleView(binding)

        viewModel.requestStatus.observe(this, Observer<String> {
            (activity as MainActivity).showErrorDialog(it)
        })

        val manufactureId = arguments?.getString(MANUFACTURE_ID_ARG)
        manufactureId?.let { id ->
            viewModel.loadManufactureDetails(id).observe(this,
                    Observer<PagedList<VehicleData>> {
                adapter.submitList(it)
            })
        }

        return binding.root
    }

    private fun initRecycleView(binding: FragmentCarsDetailsBinding) {
        adapter = DetailsAdapter()

        val recyclerView = binding.rvDetails

        recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
    }

    companion object {
        const val TAG = "ManufactureDetailsFragment"
        const val MANUFACTURE_ID_ARG = "manufacture.id.arg"

        fun newInstance(manufactureId: String): ManufactureDetailsFragment {
            val detailsFragment = ManufactureDetailsFragment()
            val args = Bundle()

            args.putString(MANUFACTURE_ID_ARG, manufactureId)
            detailsFragment.arguments = args

            return detailsFragment
        }
    }

}
