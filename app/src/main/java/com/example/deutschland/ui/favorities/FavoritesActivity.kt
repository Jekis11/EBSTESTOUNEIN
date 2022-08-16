package com.example.deutschland.ui.favorities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deutschland.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFavoritesBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.zuruck.setOnClickListener{
            finish()
        }
    }
}