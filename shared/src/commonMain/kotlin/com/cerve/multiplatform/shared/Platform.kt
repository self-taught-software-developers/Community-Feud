package com.cerve.multiplatform.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform