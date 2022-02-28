package com.example.carwashapp.model

data class Service(
    val id: String,
    val state: Int,
    val plate: String,
    val brand: String,
    val identificationClient: Int,
    val namesClient: String,
    val surnamesClient: String,
    val serviceType: String,
    val observations: String,
    val dateService: String
){
    constructor() : this("",0,"","",0, "", "", "", "", "")
}