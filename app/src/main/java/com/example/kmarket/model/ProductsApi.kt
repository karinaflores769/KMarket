package com.example.kmarket.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductsApi {
    //Conexiones que quiero lograr:
    //https://www.serverbpw.com/cm/2022-2/products.php
    //https://www.serverbpw.com/cm/2022-2/product_detail.php?id=45876

    @GET
    fun getProducts(
        @Url url: String?
    ): Call<List<Products>>

    @GET("https://www.serverbpw.com/cm/2022-2/product_detail.php")
    fun getProductsDetail(
        @Query("id") id:String?
    ): Call<ProductDetail>
}