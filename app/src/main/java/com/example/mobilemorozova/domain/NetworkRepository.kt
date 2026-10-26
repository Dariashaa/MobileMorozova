package com.example.mobilemorozova.domain

interface NetworkRepository {
    fun hasInternet(): Boolean
}