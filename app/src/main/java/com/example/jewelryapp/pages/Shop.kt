package com.example.jewelryapp.pages

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.jewelryapp.model.Product

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    onAddToCart: (Product) -> Unit,
    onAddToWishlist: (Product) -> Unit,
    onProductClick: (Product) -> Unit
) {
    // Product lists using string drawable names
    val maleNecklaces = listOf(
        Product(1, "Men's Necklace 1", 199.99, "Stylish men's necklace with modern design.", "Mens Necklace", "m_necklace1"),
        Product(2, "Men's Necklace 2", 149.99, "Elegant silver chain necklace for men.", "Mens Necklace", "m_necklace2")
    )

    val maleRings = listOf(
        Product(3, "Men's Ring 1", 99.99, "Classic men's ring made with premium materials.", "Mens Rings", "m_ring1"),
        Product(4, "Men's Ring 2", 129.99, "Bold design men's ring with a polished finish.", "Mens Rings", "m_ring2")
    )

    val femaleNecklaces = listOf(
        Product(5, "Women's Necklace 1", 179.99, "Delicate women's necklace with gemstone pendant.", "Womens Necklace", "w_necklace1"),
        Product(6, "Women's Necklace 2", 159.99, "Chic gold-plated women's necklace.", "Womens Necklace", "w_necklace2")
    )

    val femaleRings = listOf(
        Product(7, "Women's Ring 1", 89.99, "Sparkling women's ring for everyday elegance.", "Womens Rings", "w_ring1"),
        Product(8, "Women's Ring 2", 109.99, "Rose gold ring with intricate details.", "Womens Rings", "w_ring2")
    )

    var selectedCategory by remember { mutableStateOf("All") }

    // Combine and filter product list
    val allProducts = femaleNecklaces + femaleRings + maleNecklaces + maleRings

    val displayedProducts = remember(selectedCategory) {
        when (selectedCategory) {
            "Womens" -> femaleNecklaces + femaleRings
            "Mens" -> maleNecklaces + maleRings
            "Womens Necklace" -> femaleNecklaces
            "Womens Rings" -> femaleRings
            "Mens Necklace" -> maleNecklaces
            "Mens Rings" -> maleRings
            else -> allProducts
        }
    }

    Row(modifier = Modifier.fillMaxSize()) {
        // Sidebar categories
        LazyColumn(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                CategoryButton("All", selectedCategory) { selectedCategory = it }
            }
            item {
                CategorySection("Womens")
            }
            item {
                CategoryButton("Womens Necklace", selectedCategory) { selectedCategory = it }
            }
            item {
                CategoryButton("Womens Rings", selectedCategory) { selectedCategory = it }
            }
            item {
                CategorySection("Mens")
            }
            item {
                CategoryButton("Mens Necklace", selectedCategory) { selectedCategory = it }
            }
            item {
                CategoryButton("Mens Rings", selectedCategory) { selectedCategory = it }
            }
        }

        // Product display area
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(displayedProducts) { product ->
                ProductCard(product, onProductClick)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit) {
    val context = LocalContext.current
    val imageResId = remember(product.image) {
        context.resources.getIdentifier(product.image, "drawable", context.packageName)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(product) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = product.title,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(product.title, style = MaterialTheme.typography.titleMedium)
                Text("$${product.price}", fontSize = 14.sp)
                Text(product.description, fontSize = 12.sp, maxLines = 2)
            }
        }
    }
}

@Composable
fun CategoryButton(
    label: String,
    selectedCategory: String,
    onClick: (String) -> Unit
) {
    val isSelected = selectedCategory == label
    Button(
        onClick = { onClick(label) },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label, fontSize = 14.sp)
    }
}

@Composable
fun CategorySection(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
    )
}
