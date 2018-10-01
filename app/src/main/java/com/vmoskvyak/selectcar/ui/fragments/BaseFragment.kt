package com.vmoskvyak.selectcar.ui.fragments

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.selectcar.R
import com.vmoskvyak.selectcar.databinding.FragmentRecyclerViewBinding
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(), LifecycleOwner {

    private lateinit var mLifecycleRegistry: LifecycleRegistry

    protected lateinit var binding: FragmentRecyclerViewBinding
    protected lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLifecycleRegistry = LifecycleRegistry(this)
        mLifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_recycler_view, container, false)

        initRecycleView(binding)

        return binding.root
    }

    private fun initRecycleView(binding: FragmentRecyclerViewBinding) {
        recyclerView = binding.rvVehicles

        recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

    }

    override fun onStart() {
        super.onStart()
        mLifecycleRegistry.markState(Lifecycle.State.STARTED)
    }


    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

}
