package com.vmoskvyak.selectcar.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.selectcar.databinding.DetailsItemBinding
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.ui.fragments.details.DetailsItemViewModel

class DetailsAdapter : PagedListAdapter<VehicleData,
        DetailsAdapter.DetailsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DetailsItemBinding.inflate(inflater, parent, false)
        return DetailsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val manufacturerDetails = getItem(position)
        holder.binding?.viewModel = DetailsItemViewModel(manufacturerDetails)
    }

    class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: DetailsItemBinding? = DataBindingUtil.bind(view)
    }

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<VehicleData> =
                object : DiffUtil.ItemCallback<VehicleData>() {
                    override fun areItemsTheSame(oldItem: VehicleData,
                                                 newItem: VehicleData): Boolean {
                        return oldItem == newItem
                    }

                    override fun areContentsTheSame(oldItem: VehicleData,
                                                    newItem: VehicleData): Boolean {
                        return oldItem == newItem
                    }
                }
    }

}
