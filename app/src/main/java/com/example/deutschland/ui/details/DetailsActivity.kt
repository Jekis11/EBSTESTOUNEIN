package com.example.deutschland.ui.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.deutschland.databinding.ActivityDetailsBinding
import com.example.deutschland.model.Result

class DetailsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        getResultFromMainActivityAndBuildInterface()
        initButtonOnClickListeners()
    }

    private fun getResultFromMainActivityAndBuildInterface() {
        val result = intent.getParcelableExtra<Result>("result")

        binding.tvInformationDetails.movementMethod = ScrollingMovementMethod()

        Glide.with(binding.root).load(result?.main_image).into(binding.idIVCourse)
        binding.nameebs.text = result?.name.toString()
        binding.priceText.text = "$ ${result?.price.toString()}"
        binding.tvInformationDetails.text = result?.details.toString()
    }

    private fun initButtonOnClickListeners() {
        binding.btnBuyNow.setOnClickListener {
            Toast.makeText(
                this,
                "Congratulations, you have successfully purchased the item.",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.btnAddToCart.setOnClickListener {
            Toast.makeText(
                this,
                "This product was added to the cart.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}