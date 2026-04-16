package com.busra.kisileruygulamasi.data.entity

import kotlinx.serialization.Serializable


@Serializable
data class Kisiler(var kisi_id: Int,
                   var kisi_ad: String,
                   var kisi_tel: String){



}