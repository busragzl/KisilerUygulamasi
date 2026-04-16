package com.busra.kisileruygulamasi.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KisiKayitSayfa(navController: NavController) {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }

    fun kaydet(kisi_ad: String,kisi_tel: String){
        println("Kişi Kaydet: $kisi_ad - $kisi_tel")
    }
    Scaffold(
        topBar = {TopAppBar(
            title = { Text(text = "Kişi Kayıt") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = " ")
                }
            }
        ) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly, // Dikeyde eşit boşluk bırakıyor
            horizontalAlignment = Alignment.CenterHorizontally // Yatayda ortalamak için
        ) {
            TextField(
                value = tfKisiAd.value,
                onValueChange = {tfKisiAd.value = it},
                label = { Text(text = "Kişi Adı") })
            TextField(
                value = tfKisiTel.value,
                onValueChange = {tfKisiTel.value = it},
                label = { Text(text = "Kişi Tel") })
            Button(
                modifier = Modifier.size(250.dp,50.dp),
                onClick = {
                    kaydet(tfKisiAd.value,tfKisiTel.value)
            }){
                Text(text = "KAYDET")

            }
        }
    }
}