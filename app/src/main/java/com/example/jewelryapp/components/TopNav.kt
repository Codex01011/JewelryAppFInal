package com.example.jewelryapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(isLoggedIn: Boolean, onWishlistClick: () -> Unit) {
    TopAppBar(
        title = {
            Text("LoveD")
        },
        actions = {
            IconButton(onClick = onWishlistClick) {
                Icon(Icons.Filled.Favorite, contentDescription = "Wishlist") // Heart icon for Wishlist
            }
        }
    )
}

@Preview
@Composable
fun TopNavPreview() {
    TopNavBar(isLoggedIn = false) {}
}
