package com.example.jewelryapp

class CartRepository(private val dao: CartDao) {
    val cartItems = dao.getCartItems()
    suspend fun insert(item: CartItem) = dao.insert(item)
    suspend fun delete(item: CartItem) = dao.delete(item)
}
