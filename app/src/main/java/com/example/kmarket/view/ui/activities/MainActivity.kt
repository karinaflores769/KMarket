package com.example.kmarket.view.ui.activities

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmarket.R
import com.example.kmarket.databinding.ActivityMainBinding
import com.example.kmarket.model.Products
import com.example.kmarket.model.ProductsApi
import com.example.kmarket.view.adapter.Adaptador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Adaptador.OnItemListener{

    private val BASE_URL = "https://www.serverbpw.com/"
    private val LogTag = "log"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productsApi : ProductsApi = retrofit.create(ProductsApi::class.java)

        val call: Call<List<Products>> = productsApi.getProducts("cm/2022-2/products.php")

        //Métodos para recibir la respuesta del call
        call.enqueue(object : Callback<List<Products>>{
            override fun onResponse(
                call: Call<List<Products>>,
                response: Response<List<Products>>
            ) {
                Log.d(LogTag, "Respuesta del servidor: ${response.toString()}")
                Log.d(LogTag, "Datos: ${response.body().toString()}")

                binding.pbConexion.visibility = View.INVISIBLE
                //val productsTmp: Products
                //for(productsTmp in response.body()!!){
                //    Toast.makeText(this@MainActivity, "Nombre: ${productsTmp.name}", Toast.LENGTH_SHORT).show()
                //}

                //Instanciar
                val adaptador = Adaptador(this@MainActivity, response.body()!!,this@MainActivity)

                val recyclerView = binding.rvMenu

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = adaptador
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                Log.d(LogTag, "Error")
                binding.pbConexion.visibility = View.INVISIBLE
                //Toast.makeText(this@MainActivity, getString(R.string.noint), Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this@MainActivity)
                    .setTitle(getString(R.string.noint))
                    .setMessage(getString(R.string.msgnoint))
                    .setPositiveButton(getString(R.string.aceptar), DialogInterface.OnClickListener{ dialogInterface, i ->

                    })
                    .create()
                    .show()
            }

        })

    }

    override fun onItemClick(product: Products) {
        val parametros = Bundle()
        parametros.putString("id", product.id)

        val intent = Intent(this, Details::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }

}