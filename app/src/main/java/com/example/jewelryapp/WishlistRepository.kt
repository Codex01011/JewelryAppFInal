package com.example.jewelryapp



class WishlistRepository(private val dao: WishlistDao) {
    val wishlistItems = dao.getWishlistItems()
    suspend fun insert(item: WishlistItem) = dao.insert(item)
    suspend fun delete(item: WishlistItem) = dao.delete(item)
}
