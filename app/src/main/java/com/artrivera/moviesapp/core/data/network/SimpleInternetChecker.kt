package com.artrivera.moviesapp.core.data.network

import com.artrivera.moviesapp.core.domain.InternetChecker
import kotlinx.coroutines.coroutineScope
import java.net.InetSocketAddress
import java.net.Socket

object SimpleInternetChecker : InternetChecker {

    override suspend fun isInternetAvailable(): Boolean  = coroutineScope {
        return@coroutineScope try {
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            socket.connect(socketAddress, 2000)
            socket.close()
            true
        } catch (e: Exception) {
            false
        }
    }

}