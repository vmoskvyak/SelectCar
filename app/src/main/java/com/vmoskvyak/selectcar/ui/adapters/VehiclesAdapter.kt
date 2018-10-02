package com.vmoskvyak.selectcar.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.selectcar.databinding.VehicleItemEvenBinding
import com.vmoskvyak.selectcar.databinding.VehicleItemOddBinding
import com.vmoskvyak.selectcar.network.data.VehicleData
import com.vmoskvyak.selectcar.ui.adapters.listeners.OnMainTypeItemClickListener
import com.vmoskvyak.selectcar.ui.adapters.listeners.OnManufactureItemClickListener
import com.vmoskvyak.selectcar.ui.fragments.viewmodel.VehicleItemViewModel

class VehiclesAdapter(private var manufacturerId: String? = null) : PagedListAdapter<VehicleData,
        RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    var manufacturerClickListener: OnManufactureItemClickListener? = null
    var mainTypeClickListener: OnMainTypeItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == ITEM_TYPE_ODD) {
            val binding = VehicleItemOddBinding.inflate(inflater, parent, false)
            VehicleItemViewHolderOdd(binding.root)
        } else {
            val binding = VehicleItemEvenBinding.inflate(inflater, parent, false)
            VehicleItemViewHolderEven(binding.root)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            ITEM_TYPE_EVEN
        } else {
            ITEM_TYPE_ODD
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val manufacturerDetails = getItem(position)

        if (holder is VehicleItemViewHolderOdd) {
            holder.binding?.viewModel = VehicleItemViewModel(manufacturerDetails)
            holder.binding?.click = getOnVehicleItemClickListener()
        } else if (holder is VehicleItemViewHolderEven) {
            holder.binding?.viewModel = VehicleItemViewModel(manufacturerDetails)
            holder.binding?.click = getOnVehicleItemClickListener()
        }
    }

    private fun getOnVehicleItemClickListener(): OnVehicleItemClickListener {
        return object : OnVehicleItemClickListener {

            override fun onVehicleItemClick(viewModel: VehicleItemViewModel) {
                val dataId = viewModel.getDataId() ?: return

                if (manufacturerId == null) {
                    manufacturerClickListener?.onItemClick(dataId, viewModel.getText())
                } else {
                    mainTypeClickListener?.onItemClick(manufacturerId!!, dataId)
                }
            }
        }
    }

    interface OnVehicleItemClickListener {

        fun onVehicleItemClick(viewModel: VehicleItemViewModel)

    }

    class VehicleItemViewHolderOdd(view: View) : RecyclerView.ViewHolder(view) {
        val binding: VehicleItemOddBinding? = DataBindingUtil.bind(view)
    }

    class VehicleItemViewHolderEven(view: View) : RecyclerView.ViewHolder(view) {
        val binding: VehicleItemEvenBinding? = DataBindingUtil.bind(view)
    }

    companion object {
        const val ITEM_TYPE_ODD = 0
        const val ITEM_TYPE_EVEN = 1

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
