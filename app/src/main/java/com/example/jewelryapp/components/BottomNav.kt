package com.example.jewelryapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jewelryapp.R

@Composable
fun BottomNavBar(currentScreen: String, onTabSelected: (String) -> Unit) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        NavigationBarItem(
            selected = currentScreen == "home",
            onClick = { onTabSelected("home") },
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
        )
        NavigationBarItem(
            selected = currentScreen == "shop",
            onClick = { onTabSelected("shop") },
            label = { Text("Shop") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.shop_icon),
                    contentDescription = "Shop",
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        NavigationBarItem(
            selected = currentScreen == "cart",
            onClick = { onTabSelected("cart") },
            label = { Text("Cart") },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") }
        )
        NavigationBarItem(
            selected = currentScreen == "profile",
            onClick = { onTabSelected("profile") },
            label = { Text("Profile") },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") }
        )
    }
}
