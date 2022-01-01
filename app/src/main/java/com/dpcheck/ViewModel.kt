package com.dpcheck

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModel : BaseObservable() {

    @get:Bindable
    var data : DataModel?=null
    set(value) {
        field=value
        notifyPropertyChanged(BR.data)
    }

    @get:Bindable
    var username:String?=null
    set(value) {
        field=value
        notifyPropertyChanged(BR.username)
    }

    fun onClick(){
        //if(!username?.trim().equals(""))
            //getUserData()
        Log.i("pergjigja","response.body().toString()")
    }

    /*fun getUserData(){
        val call = retrofit.create(InstagramInterface::class.java).getUser("eduardmerkaj")
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    data=response.body()
                    Log.i("pergjigja",response.body().toString())
                }
                else {
                    //Toast.makeText(this@MainActivity, "Error!", Toast.LENGTH_SHORT).show()
                    Log.i("pergjigja",response.toString())
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                //Toast.makeText(this@MainActivity,"Error!", Toast.LENGTH_SHORT).show()
                Log.i("pergjigjaError",t.toString())
                //searchClicked=true
            }
        })
    }*/

}