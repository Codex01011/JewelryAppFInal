package com.example.jewelryapp.pages

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.jewelryapp.model.Product
import com.example.jewelryapp.network.RetrofitClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import com.example.jewelryapp.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    isLoggedIn: Boolean,
    viewModel: ProductViewModel,
    onAddToWishlist: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    var jewelryList by remember { mutableStateOf<List<Product>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            jewelryList = RetrofitClient.apiService.getJewelry()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    // Simulated gender split
    val forHim = jewelryList.filter { it.title.contains("men" + "necklace" + "ring", ignoreCase = true) }
        .ifEmpty { jewelryList.filterIndexed { i, _ -> i % 2 == 0 }.take(5) }
    

    val forHer = jewelryList.filter { it.title.contains("women" + "necklace" + "ring", ignoreCase = true) }
        .ifEmpty { jewelryList.filterIndexed { i, _ -> i % 2 != 0 }.take(5) }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Text("For Him", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        }
        items(forHim) { product ->
            ProductCard(product, onAddToWishlist, onAddToCart, onProductClick)
        }

        item {
            Text("For Her", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        }
        items(forHer) { product ->
            ProductCard(product, onAddToWishlist, onAddToCart, onProductClick)
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onAddToWishlist: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    onProductClick: (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProductClick(product) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = product.title,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 8.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(product.title, fontWeight = FontWeight.Bold)
                Text("$${product.price}")
                Text(
                    product.description.take(100) + "...",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = { onAddToWishlist(product) }) {
                        Text("Wishlist")
                    }
                    Button(onClick = { onAddToCart(product) }) {
                        Text("Add to Cart")
                    }
                }
            }
        }
    }
}
