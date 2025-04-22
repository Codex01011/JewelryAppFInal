package com.example.jewelryapp.network

import com.example.jewelryapp.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products/category/jewelery")
    suspend fun getJewelry(): List<Product>
}
