package com.dpcheck

import android.app.DownloadManager
import android.app.Service
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

class ViewModel(private val retrofit: Retrofit) : BaseObservable() {

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
            if(imageUrl.length>0)
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
                }
                else {
                    toastMessage = if(response.code()==404)
                        "No user found"
                    else
                        response.toString()
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                toastMessage=t.toString()
            }
        })
    }

    fun downloadImage(view:View){
        if(imageUrl.length==0)
            return
        val imageUri=Uri.parse(imageUrl)
        val downloadRequest=DownloadManager.Request(imageUri)
            .setTitle("Image downloaded")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,SimpleDateFormat("ddMMyyyy_hhmmss",Locale.getDefault()).format(Date())+".png")
            .setAllowedOverMetered(true)
        val downloadManager=view.context.getSystemService(Service.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(downloadRequest)
    }

}