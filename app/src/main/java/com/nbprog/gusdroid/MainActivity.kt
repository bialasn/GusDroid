package com.nbprog.gusdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.nbprog.gusdroid.gus.Gus
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            val result = Gus.authenticate("abcde12345abcde12345")
            val info = Gus.getInfoBy(regon = "000331501")
            info
        }
    }
}