package com.dpcheck

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface InstagramInterface {
    @GET("{username}/?__a=1")
    fun getUser(@Path("username") username:String) : Call<DataModel>
}