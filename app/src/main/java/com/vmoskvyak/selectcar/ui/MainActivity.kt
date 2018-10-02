package com.vmoskvyak.selectcar.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import com.vmoskvyak.selectcar.R
import com.vmoskvyak.selectcar.databinding.ActivityMainBinding
import com.vmoskvyak.selectcar.ui.fragments.ManufacturersFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main)

        initToolbar(binding, savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_container, ManufacturersFragment(), ManufacturersFragment.TAG)
                    .commit()
        }
    }

    private fun initToolbar(binding: ActivityMainBinding,
                            savedInstanceState: Bundle?) {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        toolbar.title = savedInstanceState?.getString("title")
    }

    fun setToolbarTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun showErrorDialog(message: String?) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Error")
        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

}
