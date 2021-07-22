package com.example.pokedex.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedex.R
import com.example.pokedex.ui.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Pokedex)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}