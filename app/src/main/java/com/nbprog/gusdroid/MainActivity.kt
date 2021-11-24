package com.nbprog.gusdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.nbprog.gusdroidlib.gus.Gus
import com.nbprog.gusdroidlib.model.GusResult
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            val result = Gus.authenticate("b0663cba094245d8802b")
            val info = Gus.getInfoBy(nip = "7681841967")
            Log.i("GUS",info.toString())
            //TODO: there is necessary to check in GusResult.Success if authorization e.x is null
            when(info){
                is GusResult.GenericError -> { }
                GusResult.NetworkError -> { }
                is GusResult.ParsingError -> { }
                is GusResult.Success -> { }
            }
        }
    }
}