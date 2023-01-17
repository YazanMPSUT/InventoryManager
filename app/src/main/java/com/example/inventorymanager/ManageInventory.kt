package com.example.inventorymanager

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.inventorymanager.MainActivity.Companion.CurrentBalance

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

    @SuppressLint("Range", "SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.R)
    fun addOrSub(operation:String){
        val pid = ETpid?.text.toString()
        val qtyChange = ETqty?.text.toString()

        val values = ContentValues()

        //TODO: Add cursor here to get the current value of quantity. OR just make it new value.
        //val args : Array<String>


        val crs = contentResolver.query(InventoryProvider.CONTENT_URI,
            arrayOf(InventoryProvider.quantity,
                InventoryProvider.buy_price,
                InventoryProvider.sell_price
            ),
        "${InventoryProvider._id} = ?",
        Array<String>(1){pid},
        null)

        var qtyCurrent : String = "0"
        var priceBuy : Double = 0.00
        var priceSell : Double = 0.00
            if (crs!!.moveToFirst()) {
                do {
                    val qty: String? = crs.getString(crs.getColumnIndex(InventoryProvider.quantity))
                    qtyCurrent = qty!!
                    val pB: String? = crs.getString(crs.getColumnIndex(InventoryProvider.buy_price))
                    priceBuy = pB!!.toDouble()
                    val pS: String? = crs.getString(crs.getColumnIndex(InventoryProvider.sell_price))
                    priceSell = pS!!.toDouble()
                } while (crs.moveToNext())
            }

        crs.close()
        var qtyNew : Int =
            if(operation == "+") {
                (qtyCurrent.toInt() + qtyChange.toInt())
            }
            else {
                 (qtyCurrent.toInt() - qtyChange.toInt())
            }

        if (operation == "+")
            CurrentBalance -= qtyChange.toInt() * priceBuy
        else
            CurrentBalance += qtyChange.toInt() * priceSell

        values.put(/* key = */ InventoryProvider.quantity,
            /* value = */ "$qtyNew"
        )

        contentResolver.update(InventoryProvider.CONTENT_URI,
            values,
            "${InventoryProvider._id} = ?",Array(1){pid})

        getSharedPreferences("PREFS", MODE_PRIVATE).edit().putString("CurrentBalance",
            CurrentBalance.toString()).apply()
    }

    @SuppressLint("SuspiciousIndentation")
    fun receiveAndDelete(id:String, name:String) {

        val count : Int = contentResolver.delete(
        InventoryProvider.CONTENT_URI,
        "${InventoryProvider._id}  = ? AND ${InventoryProvider.name} = ?",
            arrayOf(id,name)
        )

        if(count>0)
          Toast.makeText(this,"Item \"$name\" with id = $id has successfully been removed",
                Toast.LENGTH_LONG).show()
        getSharedPreferences("PREFS", MODE_PRIVATE).edit().putString("CurrentBalance",
            CurrentBalance.toString()).apply()
    }
}