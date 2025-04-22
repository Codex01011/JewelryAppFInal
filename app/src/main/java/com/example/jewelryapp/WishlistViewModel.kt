package com.example.jewelryapp

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WishlistViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WishlistRepository
    val wishlistItems: LiveData<List<WishlistItem>>

    init {
        val db = AppDatabase.getDatabase(application)
        repository = WishlistRepository(db.wishlistDao())
        wishlistItems = repository.wishlistItems.asLiveData()
    }

    fun insert(item: WishlistItem) = viewModelScope.launch {
        repository.insert(item)
    }

    fun delete(item: WishlistItem) = viewModelScope.launch {
        repository.delete(item)
    }
}
