package com.busra.kisileruygulamasi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform