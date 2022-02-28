package com.example.carwashapp.utils

import com.example.carwashapp.model.Service

class ServiceDetailState (
    val isLoading: Boolean = false,
    val service: Service? = null,
    val error: String = ""
)