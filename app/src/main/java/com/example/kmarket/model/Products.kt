package com.example.kmarket.model

import com.google.gson.annotations.SerializedName

class Products {
    //Mapeo respuestas
    //id
    @SerializedName("id")
    var id: String? = null
    //name
    @SerializedName("name")
    var name: String? = null
    //imagen
    @SerializedName("thumbnail_url")
    var img: String? = null
    //price
    @SerializedName("price")
    var price: String? = null
    //provider
    @SerializedName("provider")
    var prov: String? = null
    //provider
    @SerializedName("delivery")
    var cdelivery: String? = null

}