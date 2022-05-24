package com.example.kmarket.view.ui.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.kmarket.R
import com.example.kmarket.databinding.ActivityDetailsBinding
import com.example.kmarket.model.ProductDetail
import com.example.kmarket.model.Products
import com.example.kmarket.model.ProductsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Details : AppCompatActivity() {

    private lateinit var binding:ActivityDetailsBinding

    private val BASE_URL = "https://www.serverbpw.com/"
    private val LogTag = "log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getString("id", "0")

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productsApi : ProductsApi = retrofit.create(ProductsApi::class.java)

        val call: Call<ProductDetail> = productsApi.getProductsDetail(id)

        call.enqueue(object : Callback<ProductDetail>{
            override fun onResponse(call: Call<ProductDetail>, response: Response<ProductDetail>) {
                with(binding){
                    pbConexion.visibility = View.INVISIBLE

                    tvTitle.text = response.body()?.nameD
                    tvDesc.text = response.body()?.descD

                    //imagen
                    Glide.with(this@Details)
                        .load(response.body()?.imgD)
                        .into(ivImage)
                }
            }

            override fun onFailure(call: Call<ProductDetail>, t: Throwable) {
                Log.d(LogTag, "Error")
                binding.pbConexion.visibility = View.INVISIBLE
                //Toast.makeText(this@Details, getString(R.string.noint), Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this@Details)
                    .setTitle(getString(R.string.noint))
                    .setMessage(getString(R.string.msgnoint))
                    .setPositiveButton(getString(R.string.aceptar), DialogInterface.OnClickListener{ dialogInterface, i ->

                    })
                    .create()
                    .show()
            }

        })

    }
}