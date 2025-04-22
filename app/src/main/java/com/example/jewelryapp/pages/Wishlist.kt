package com.example.jewelryapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jewelryapp.model.Product

@Composable
fun WishlistScreen(
    wishlist: List<Product>,
    isLoggedIn: Boolean,
    onProductClick: (Product) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Back to Home")
        }

        Text(
            text = "Wishlist",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display wishlist items
        if (wishlist.isEmpty()) {
            Text("Your wishlist is empty.")
        } else {
            Column {
                wishlist.forEach { product ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onProductClick(product) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = product.image.toInt()),
                            contentDescription = product.title,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 16.dp)
                        )
                        Text(product.title, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
