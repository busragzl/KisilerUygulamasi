package com.busra.kisileruygulamasi.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.busra.kisileruygulamasi.data.entity.Kisiler
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController) {
    Scaffold(
        topBar = {TopAppBar(title = { Text(text = "Kişiler") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("kisikayitsayfa")},
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription =  " ")
                }
            )

        }
    ) {paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly, // Dikeyde eşit boşluk bırakıyor
            horizontalAlignment = Alignment.CenterHorizontally // Yatayda ortalamak için
        ) {
            Button(onClick = {
                val kisi = Kisiler(1, "Ahmet", "123456789")
                val kisiJson = Json.encodeToString(kisi)

                navController.navigate("kisidetaysayfa/$kisiJson")
            }){
                Text(text = "Detay")

            }
        }
    }
}