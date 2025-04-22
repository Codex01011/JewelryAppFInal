package com.example.jewelryapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jewelryapp.R

data class User(
    val name: String,
    val email: String,
    val address: String,
    val country: String,
    val currency: String,
    val language: String,
    val paymentMethod: String
)

@Composable
fun ProfileScreen(
    user: User,
    modifier: Modifier = Modifier,
    onNavigateToWishlist: () -> Unit = {},
    onNavigateToCoupons: () -> Unit = {},
    onNavigateToPastOrders: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onLogOut: () -> Unit = {},
    navController: NavController
) {
    val couponExpanded = remember { mutableStateOf(false) }
    val pastOrdersExpanded = remember { mutableStateOf(false) }

    val pastOrders = listOf(
        "Order #001 - Diamond Ring - $1200",
        "Order #002 - Gold Necklace - $850",
        "Order #003 - Silver Earrings - $450"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "User Profile Image",
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = user.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Wishlist Button
        Button(
            onClick = onNavigateToWishlist,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "View Wishlist", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Coupons Button
        Button(
            onClick = { couponExpanded.value = !couponExpanded.value },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Coupons", fontSize = 16.sp)
        }

        if (couponExpanded.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .background(Color(0xFFF0F0F0))
                    .padding(12.dp)
            ) {
                Text(
                    text = "🎉 20% off!",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Past Orders Button
        Button(
            onClick = { pastOrdersExpanded.value = !pastOrdersExpanded.value },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Past Orders", fontSize = 16.sp)
        }

        if (pastOrdersExpanded.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .background(Color(0xFFF0F0F0))
                    .padding(12.dp)
            ) {
                if (pastOrders.isEmpty()) {
                    Text(
                        text = "No past orders available",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                } else {
                    Column {
                        pastOrders.forEach { order ->
                            Text(text = order, fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Settings Button
        Button(
            onClick = onNavigateToSettings,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Settings", fontSize = 16.sp)
        }


        val returnsExpanded = remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(12.dp))

        // Returns Button
        Button(
            onClick = { returnsExpanded.value = !returnsExpanded.value },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Returns", fontSize = 16.sp)
        }

        if (returnsExpanded.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .background(Color(0xFFF0F0F0))
                    .padding(12.dp)
            ) {
                Text(
                    text = "If you'd like to process a return, please contact us at:\nreturns@jewelryapp.com",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }


        Spacer(modifier = Modifier.height(12.dp))

        // Logout
        TextButton(onClick = onLogOut) {
            Text(text = "Log Out", color = MaterialTheme.colorScheme.error)
        }
    }
}

