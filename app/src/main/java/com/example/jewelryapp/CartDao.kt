package com.example.jewelryapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem): Long  // Returns row ID

    @Delete
    suspend fun delete(cartItem: CartItem): Int  // Returns number of rows affected

    @Query("SELECT * FROM cart_items")
    fun getCartItems(): Flow<List<CartItem>>
}
