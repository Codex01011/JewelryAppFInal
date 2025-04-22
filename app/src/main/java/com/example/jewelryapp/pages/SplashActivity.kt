package com.example.jewelryapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jewelryapp.navigation.Routes
import kotlinx.coroutines.delay
//import com.example.jewelryapp.R

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Routes.LOGIN) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
        //    Image(
      //          painter = painterResource(id = R.drawable.loved),
         //         contentDescription = "App Logo",
    //            modifier = Modifier.size(200.dp)
   //         )
            Spacer(modifier = Modifier.height(20.dp))
            Text("Jewelry App", style = MaterialTheme.typography.headlineMedium)
        }
    }
}