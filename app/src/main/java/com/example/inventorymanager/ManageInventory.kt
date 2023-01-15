package com.example.inventorymanager

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class ManageInventory : AppCompatActivity() {
    private var ETpid : EditText? = null
    private var ETqty : EditText? = null

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_inventory)

        ETpid = findViewById(R.id.ETpid)
        ETqty =  findViewById(R.id.ETqty)

        val btnAddQty : Button = findViewById(R.id.btnAddQty)
        val btnSubQty : Button = findViewById(R.id.btnSubQty)


        btnAddQty.setOnClickListener{
            addOrSub("+")
        }

        btnSubQty.setOnClickListener {
            addOrSub("-")
        }

        val btnDF : Button = findViewById(R.id.btnShowDelFrag)


        btnDF.setOnClickListener {
            val deleteFragment = DeleteDialogFragment()
            deleteFragment.show(supportFragmentManager,"Delete Dialog")
        }

    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun addOrSub(operation:String){
        val pid = ETpid?.text.toString()
        val qty = ETqty?.text.toString()

        val values = ContentValues()

        //TODO: Add cursor here to get the current value of quantity. OR just make it new value.
        values.put(InventoryProvider.quantity,
            InventoryProvider.quantity + " " + operation + " " + qty)

        contentResolver.update(InventoryProvider.CONTENT_URI,
            values,
            "${InventoryProvider._id} = ?",Array(1){pid})
    }

    fun receiveAndDelete(id:String,name:String) {

        val count : Int = contentResolver.delete(
        InventoryProvider.CONTENT_URI,
        "${InventoryProvider._id}  = ? ",
            Array(1){id}
        )

        Toast.makeText(this,"Item \"$name\" with id = $id has successfully been removed",
            Toast.LENGTH_LONG).show()
    }
}