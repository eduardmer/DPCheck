package com.dpcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.dpcheck.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    /*lateinit var usernameText:EditText
    lateinit var imageView:ImageView
    lateinit var nameView:TextView
    lateinit var biographyView:TextView
    lateinit var followersView:TextView
    lateinit var followingView:TextView*/
    private lateinit var retrofit : Retrofit
    var searchClicked:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.viewModel=ViewModel()
        supportActionBar?.hide()
        retrofit=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.instagram.com/")
            .build()
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //val view = binding.root
        //setContentView(view)

        searchClicked=true
    }

    /*fun init(){
        usernameText=findViewById(R.id.usernameText)
        imageView=findViewById(R.id.imageView)
        nameView=findViewById(R.id.name_view)
        biographyView=findViewById(R.id.biography_view)
        followersView=findViewById(R.id.followers_view)
        followingView=findViewById(R.id.following_view)
        searchClicked=true
    }*/

    /*fun buttonClicked(view : View){
        when(view){
            binding.searchButton -> {
                if(!binding.usernameText.text.toString().trim().equals("") && searchClicked)
                    getUserData()
            }
        }
    }

    private fun getUserData(){
        searchClicked=false

        val call = retrofit.create(InstagramInterface::class.java).getUser(binding.usernameText.text.toString().trim())
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                searchClicked=true
                if (response.isSuccessful) {
                    val dataModel=response.body()

                    Glide.with(this@MainActivity).load(dataModel?.graphql?.user?.profile_pic_url_hd).into(binding.imageView)
                    if(!dataModel?.graphql?.user?.full_name.equals("")){
                        binding.fullName.setText(dataModel?.graphql?.user?.full_name)
                        binding.fullName.visibility=View.VISIBLE
                    }
                    else
                        binding.fullName.visibility=View.INVISIBLE
                    if(!dataModel?.graphql?.user?.biography.equals("")){
                        binding.biographyView.text = dataModel?.graphql?.user?.biography
                        binding.biographyView.visibility=View.VISIBLE
                    }
                    else
                        binding.biographyView.visibility=View.INVISIBLE
                    binding.followersView.setText("${dataModel?.graphql?.user?.edge_followed_by?.count}")
                    binding.followersView.visibility=View.VISIBLE
                    binding.followingView.setText("${dataModel?.graphql?.user?.edge_follow?.count}")
                    binding.followingView.visibility=View.VISIBLE
                }
                else {
                    Toast.makeText(this@MainActivity, "Error!", Toast.LENGTH_SHORT).show()
                    Log.i("pergjigja",response.toString())
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error!",Toast.LENGTH_SHORT).show()
                Log.i("pergjigjaError",t.toString())
                searchClicked=true
            }
        })
    }*/
}