package com.example.carwashapp.utils

import com.example.carwashapp.model.Service

class ServiceListState(
    val isLoading: Boolean = false,
    val services: List<Service> = emptyList(),
    val error: String = ""
)