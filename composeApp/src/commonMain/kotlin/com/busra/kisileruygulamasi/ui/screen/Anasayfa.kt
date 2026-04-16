package com.busra.kisileruygulamasi.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.busra.kisileruygulamasi.data.entity.Kisiler
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController) {
    val aramaYapılıyorMu= remember { mutableStateOf(false) }
    val tfSearch= remember { mutableStateOf("") }
    val kisilerListesi = remember { mutableStateListOf<Kisiler>() }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true){
        // Sayfa açıldığında listeye kişileri ekleme
        val k1 = Kisiler(1, "Ahmet", "123456789")
        val k2 = Kisiler(2, "Mehmet", "987654321")
        val k3 = Kisiler(3, "Zeynep", "555666777")
        kisilerListesi.add(k1)
        kisilerListesi.add(k2)
        kisilerListesi.add(k3)
        println("Kişi eklendi: $kisilerListesi")
    }
    fun ara(aramaKelime: String){
        println("Kişi ara: $aramaKelime")
    }
    fun sil(kisi_id: Int){
        println("Kişi Sil: $kisi_id")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if(aramaYapılıyorMu.value){
                        TextField(
                            value = tfSearch.value,
                            onValueChange = {
                                tfSearch.value = it
                                ara(aramaKelime = it)
                            },
                            label = { Text(text = "Ara")},
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedLabelColor = Color.Transparent,
                                unfocusedLabelColor = Color.Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,

                                )
                        )
                    }else{
                        Text(text = "Kişiler")
                    }
                },
                actions = {
                    if(aramaYapılıyorMu.value){
                        IconButton(onClick = {
                            aramaYapılıyorMu.value = false
                            tfSearch.value = ""
                        }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = " ")
                        }

                    }else{
                        IconButton(onClick = {
                            aramaYapılıyorMu.value = true
                        }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = " ")
                        }
                    }

                }

            ) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("kisikayitsayfa")},
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription =  " ")
                }
            )

        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {paddingValues ->
        //Liste olduğu için lazycolumn kullanıyoruz
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            items(
                count = kisilerListesi.count(),
                itemContent ={//1,2,3
                    val kisi = kisilerListesi[it]
                    Card(modifier = Modifier.padding(all = 5.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth().clickable{
                                val kisiJson = Json.encodeToString(kisi)
                                navController.navigate("kisidetaysayfa/$kisiJson")
                            },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.padding(all = 10.dp)) {
                                Text(text = kisi.kisi_ad, fontSize = 20.sp)
                                Spacer(modifier = Modifier.size(10.dp))
                                Text(text = kisi.kisi_tel)
                            }
                            IconButton(onClick = {
                                scope.launch {
                                    val sb = snackbarHostState.showSnackbar(message = "${kisi.kisi_ad} silinsin mi?", actionLabel = "Evet")
                                    if(sb == SnackbarResult.ActionPerformed){
                                        sil(kisi.kisi_id)
                                    }
                                }

                            }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = " ")
                            }
                        }
                    }
                }
            )

        }
    }
}