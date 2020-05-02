package com.example.forecastapplication.view.ui.weather.current

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.forecastapplication.R

class Dialog : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater: LayoutInflater = requireActivity().layoutInflater

        val view: View = inflater.inflate(R.layout.dialog, null)

        builder.setView(view)
            .setTitle("Enter your city")
            .setNegativeButton(
                "cancel"
            ) { dialog, which -> }
            .setPositiveButton(
                "Accept"
            ) { dialog, which -> }

        return builder.create()
    }
}