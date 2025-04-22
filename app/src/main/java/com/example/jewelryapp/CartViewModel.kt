package com.example.jewelryapp

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CartRepository
    val cartItems: LiveData<List<CartItem>>

    init {
        val db = AppDatabase.getDatabase(application)
        repository = CartRepository(db.cartDao())
        cartItems = repository.cartItems.asLiveData()
    }

    fun insert(item: CartItem) = viewModelScope.launch {
        repository.insert(item)
    }

    fun delete(item: CartItem) = viewModelScope.launch {
        repository.delete(item)
    }
}
