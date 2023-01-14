package com.example.inventorymanager

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.security.Provider

class DeleteDialogFragment : DialogFragment(R.layout.dialogfragment_delete){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btCancel : Button = view.findViewById(R.id.btnCancel)
        val btConfirm : Button = view.findViewById(R.id.btnConfirm)

        btCancel.setOnClickListener {
            dismiss()
        }
        val ddl_ip = InventoryProvider()

        btConfirm.setOnClickListener {
            val idET :EditText = view.findViewById(R.id.ddf_pidET)
            val nameET : EditText = view.findViewById(R.id.ddf_pnameET)
            val id = idET.text.toString()
            val name = nameET.text.toString()

            try {
                ddl_ip.delete(
                    InventoryProvider.CONTENT_URI,
                    InventoryProvider._id + " = " + id + " AND " +
                            InventoryProvider.name + " = " + name, null
                )
                Toast.makeText(context,"Item \"$name\" with id = $id has successfully been removed",
                    Toast.LENGTH_LONG).show()
            }
            catch(e:java.lang.Exception)
            {
                Toast.makeText(context,"ya fucked up", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
            finally {
                dismiss()
            }
        }

    }


}