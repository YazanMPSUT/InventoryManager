package com.example.inventorymanager

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment

class DeleteDialogFragment : DialogFragment(R.layout.dialogfragment_delete){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btCancel : Button = view.findViewById(R.id.btCancel)
        val btConfirm : Button = view.findViewById(R.id.btConfirm)

        btCancel.setOnClickListener {

        }

        btConfirm.setOnClickListener {

        }

    }


}