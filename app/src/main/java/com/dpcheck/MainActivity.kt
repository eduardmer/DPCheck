package com.dpcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var usernameText:EditText?=null
    var imageView:ImageView?=null
    var retrofit : Retrofit?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        init()

        retrofit=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.instagram.com/")
            .build()
    }

    fun init(){
        usernameText=findViewById(R.id.usernameText)
        imageView=findViewById(R.id.imageView);
    }

    fun buttonClicked(view : View){
        when(view.id){
            R.id.searchButton -> {
                if(!usernameText!!.text.toString().trim().equals(""))
                    getUserData()
            }
        }
    }

    fun getUserData(){
        val call = retrofit!!.create(InstagramInterface::class.java).getUser(usernameText!!.text.toString().trim())
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    val dataModel=response.body()
                    Log.i("pergjigja",dataModel!!.graphql!!.user!!.full_name+"")
                }
                else
                    Toast.makeText(this@MainActivity,"Error!",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {

            }
        })
    }
}