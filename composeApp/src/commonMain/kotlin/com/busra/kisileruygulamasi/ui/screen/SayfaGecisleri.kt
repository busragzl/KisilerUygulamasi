package com.busra.kisileruygulamasi.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.busra.kisileruygulamasi.data.entity.Kisiler
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
fun SayfaGecisleri(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa"){
        composable("anasayfa") {
            Anasayfa(navController)
        }
        composable("kisikayitsayfa") {
            KisiKayitSayfa(navController)
        }
        composable("kisidetaysayfa/{kisi}",
            arguments = listOf(
                navArgument("kisi"){type = NavType.StringType}
            )) {
            val json = it.arguments?.getString("kisi")!!
            val nesne = Json.decodeFromString<Kisiler>(json)
            KisiDetaySayfa(navController, gelenKisi = nesne)
        }
    }

}