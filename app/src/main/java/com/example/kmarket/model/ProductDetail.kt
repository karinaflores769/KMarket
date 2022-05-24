package com.example.kmarket.model

import com.google.gson.annotations.SerializedName

class ProductDetail {
    //Mapeo respuestas
    //name
    @SerializedName("name")
    var nameD: String? = null
    //imagen
    @SerializedName("imag_url")
    var imgD: String? = null
    //descripción
    @SerializedName("desc")
    var descD: String? = null
}