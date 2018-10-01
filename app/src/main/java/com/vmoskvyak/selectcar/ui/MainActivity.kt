package com.vmoskvyak.selectcar.ui

import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.vmoskvyak.selectcar.R
import com.vmoskvyak.selectcar.ui.fragments.main.ManufacturersFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_container, ManufacturersFragment(), ManufacturersFragment.TAG)
                .commit()
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
