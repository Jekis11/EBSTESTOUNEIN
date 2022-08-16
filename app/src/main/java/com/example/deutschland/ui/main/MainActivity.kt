package com.example.deutschland.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.deutschland.adapters.MainScreenAdapter
import com.example.deutschland.adapters.MainOnClickListener
import com.example.deutschland.databinding.ActivityMainBinding
import com.example.deutschland.model.Result
import com.example.deutschland.repository.Repository
import com.example.deutschland.ui.favorities.FavoritesActivity
import com.example.deutschland.ui.details.DetailsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val repository = Repository()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        Toast.makeText(this, "Herzlichen Willkommen EBS", Toast.LENGTH_SHORT).show()
        init()
    }

    private fun init() {
        favoritesClickListener()
        userClickListener()
        getAllCourses()
    }

    private fun favoritesClickListener() = binding.favorites.setOnClickListener {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }

    private fun userClickListener() = binding.user.setOnClickListener {
        Toast.makeText(
            this,
            "Hallo, es tut mir leid, dieser Funktion nicht arbeiten",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getAllCourses() {
        recyclerView = binding.idRVCourses
        adapter = MainScreenAdapter(object : MainOnClickListener {
            override fun onClickOnItem(result: Result) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("result", result)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val a = repository.getAllCourses()
            if (a.isSuccessful) {
                val data = a.body()
                val list = mutableListOf<Result>()
                data?.results?.forEach {
                    list.add(it)
                }
                adapter.setNewList(list)
            }
            withContext(Dispatchers.Main) {
                binding.idPBLoading.visibility = View.GONE
            }
        }
    }
}