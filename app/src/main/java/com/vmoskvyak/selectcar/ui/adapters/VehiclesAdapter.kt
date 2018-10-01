package com.vmoskvyak.selectcar.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.selectcar.databinding.VehicleItemBinding
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.ui.adapters.listeners.OnMainTypeItemClickListener
import com.vmoskvyak.selectcar.ui.adapters.listeners.OnManufactureItemClickListener
import com.vmoskvyak.selectcar.ui.fragments.viewmodel.VehicleItemViewModel

class VehiclesAdapter(private var manufacturerId: String? = null) : PagedListAdapter<VehicleData,
        VehiclesAdapter.DetailsViewHolder>(DIFF_CALLBACK) {

    var manufacturerClickListener: OnManufactureItemClickListener? = null
    var mainTypeClickListener: OnMainTypeItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = VehicleItemBinding.inflate(inflater, parent, false)
        return DetailsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val manufacturerDetails = getItem(position)
        holder.binding?.viewModel = VehicleItemViewModel(manufacturerDetails)

        holder.binding?.click = object : OnVehicleItemClickListener {

            override fun onVehicleItemClick(viewModel: VehicleItemViewModel) {
                val dataId = viewModel.getDataId() ?: return

                if (manufacturerId == null) {
                    manufacturerClickListener?.onItemClick(dataId)
                } else {
                    mainTypeClickListener?.onItemClick(manufacturerId!!, dataId)
                }
            }

        }
    }

    interface OnVehicleItemClickListener {

        fun onVehicleItemClick(viewModel: VehicleItemViewModel)

    }

    class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: VehicleItemBinding? = DataBindingUtil.bind(view)
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
