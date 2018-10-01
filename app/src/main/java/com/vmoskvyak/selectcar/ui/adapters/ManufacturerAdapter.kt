package com.vmoskvyak.selectcar.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.selectcar.databinding.ManufacturerItemBinding
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.ui.fragments.main.ManufacturerItemViewModel

class ManufacturerAdapter : PagedListAdapter<VehicleData,
        ManufacturerAdapter.ManufacturerViewHolder>(sDiffCallback) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ManufacturerItemBinding.inflate(inflater, parent, false)
        return ManufacturerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ManufacturerViewHolder, position: Int) {
        val manufacturer = getItem(position)
        holder.binding?.viewModel = ManufacturerItemViewModel(manufacturer)

        holder.binding?.click = object : OnManufacturerItemClickListener {
            override fun onManufacturerItemClick(viewModel: ManufacturerItemViewModel) {
                viewModel.getManufacturerId()?.let {
                    onItemClickListener?.onItemClick(it)
                }
            }
        }
    }

    class ManufacturerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ManufacturerItemBinding? = DataBindingUtil.bind(view)

    }

    interface OnManufacturerItemClickListener {

        fun onManufacturerItemClick(viewModel: ManufacturerItemViewModel)

    }

    companion object {
        var sDiffCallback: DiffUtil.ItemCallback<VehicleData> =
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
