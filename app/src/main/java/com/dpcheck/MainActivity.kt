package com.dpcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.dpcheck.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        supportActionBar?.hide()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.instagram.com/")
            .build()
        binding.viewModel = ViewModel(retrofit)
    }

    companion object {
        @BindingAdapter("toastMessage")
        @JvmStatic
        fun toastMessage(view: View, toastMessage: String) {
            if(toastMessage != "")
            Toast.makeText(view.context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }

}