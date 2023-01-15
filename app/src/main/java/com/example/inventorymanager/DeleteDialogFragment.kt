package com.example.inventorymanager

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import android.content.ContentValues
import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity
import java.security.Provider
import android.content.*
import java.nio.file.Files.delete


class DeleteDialogFragment : DialogFragment(R.layout.dialogfragment_delete){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btCancel : Button = view.findViewById(R.id.btnCancel)
        val btConfirm : Button = view.findViewById(R.id.btnConfirm)

        btCancel.setOnClickListener {
            dismiss()
        }


        btConfirm.setOnClickListener {
            val idET :EditText = view.findViewById(R.id.ddf_pidET)
            val nameET : EditText = view.findViewById(R.id.ddf_pnameET)
            val id = idET.text.toString()
            val name = nameET.text.toString()

            val caller :ManageInventory = getActivity() as ManageInventory
            caller.receiveAndDelete(id,name)
            dismiss()
            }
        }

    }
