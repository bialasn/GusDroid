package com.nbprog.gusdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.nbprog.gusdroid.gus.Gus
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            val result = Gus.authenticate("b0663cba094245d8802b")
            val info = Gus.getInfoBy(nip = "7681841967")
            Log.i("GUS",info.toString())
            info
        }
    }
}