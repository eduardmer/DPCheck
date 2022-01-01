package com.dpcheck

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ViewModel(val retrofit: Retrofit) : BaseObservable() {

    @get:Bindable
    var data:DataModel?=null
    set(value) {
        field=value
        notifyPropertyChanged(BR.data)
    }

    @get:Bindable
    var username:String=""
    set(value) {
        field=value
        notifyPropertyChanged(BR.username)
    }

    @get:Bindable
    var imageUrl:String=""
    set(value) {
        field=value
        notifyPropertyChanged(BR.imageUrl)
    }

    @get:Bindable
    var toastMessage=""
    set(value) {
        field=value
        notifyPropertyChanged(BR.toastMessage)
    }

    fun onClick(){
        if(username.trim() != "")
            getUserData()

    }

    companion object{
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(imageView:ImageView,imageUrl:String){
            if(imageUrl!="")
                Glide.with(imageView.context).load(imageUrl).into(imageView)
        }
    }

    private fun getUserData(){
        val call = retrofit.create(InstagramInterface::class.java).getUser(username)
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    data=response.body()
                    imageUrl= data?.graphql?.user?.profile_pic_url_hd.toString()
                    Log.i("pergjigja",response.body().toString())
                }
                else {
                    toastMessage=response.toString()
                    Log.i("pergjigja",response.toString())
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                toastMessage=t.toString()
                Log.i("pergjigjaError",t.toString())
            }
        })
    }

}