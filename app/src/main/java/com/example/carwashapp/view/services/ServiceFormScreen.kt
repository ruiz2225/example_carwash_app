package com.example.carwashapp.view.services

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.carwashapp.R
import com.example.carwashapp.ui.theme.GreenOk

@Composable
fun ServiceFormScreen(){
    Scaffold(topBar = {
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.desc_icon_button),
                modifier = Modifier
                    .clickable {

                    }
                    .padding(start = 16.dp))
            Text(text = stringResource(id = R.string.text_topBar),
                modifier = Modifier.padding(start = 32.dp))
        }
    }) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            FormService()
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(48.dp), onClick = { /*Acción para guardar los datos del nuevo servicio*/ }) {
                Icon(modifier = Modifier.padding(end = 8.dp), imageVector = Icons.Filled.Send, contentDescription = stringResource(id = R.string.desc_icon_button))
                Text(text = stringResource(id = R.string.btn_newService))
            }
        }
    }
}

@Composable
fun FormService(){
    TitleNewService()
    Plate()
    IdentificationClient()
    NamesClient()
    SurnamesClient()
    BrandCar()
    ServiceType()
    Observations()
}

@Composable
fun TitleNewService(){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        text = stringResource(id = R.string.text_title_serviceForm),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6,
    )
}

@Composable
fun Plate(){
    var value by remember { mutableStateOf("") }
    val maxCharacter = 6
    var stateField by remember { mutableStateOf(0) }
    var isError by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = {
            if(it.length <= maxCharacter){
                value = it
            }
            if(it.length > 0 && it.length < maxCharacter){
                stateField = 1
                isError = it.length < maxCharacter
            }else{
                stateField = 2
                isError = false
            }
        },
        trailingIcon = {
            when(stateField){
                1 -> {
                    Icon(imageVector = Icons.Default.Info,
                        contentDescription = stringResource(id = R.string.desc_icon_field_text))
                }
                2 -> {
                    Icon(imageVector = Icons.Default.CheckCircle,
                        contentDescription = stringResource(id = R.string.desc_icon_field_text),
                        tint = GreenOk)
                }
            }
        },
        isError = isError,
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.ft_plate)) },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
        singleLine = true
    )

}

@Composable
fun IdentificationClient(){
    var value by remember { mutableStateOf("") }
    val maxCharacter = 10
    val minCharacter = 7
    var stateField by remember { mutableStateOf(0) }
    var isError by remember { mutableStateOf(false) }
    OutlinedTextField(value = value,
        onValueChange = {
            if(it.length <= maxCharacter){
                value = it
            }
            if(it.length > 0 && it.length < minCharacter){
                stateField = 1
                isError = it.length < minCharacter
            }else{
                stateField = 2
                isError = false
            }
        },
        trailingIcon = {
            when(stateField){
                1 -> {
                    Icon(imageVector = Icons.Default.Info, contentDescription = stringResource(id = R.string.desc_icon_field_text))
                }
                2 -> {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = stringResource(id = R.string.desc_icon_field_text))
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(id = R.string.ft_identification)) },
        singleLine = true,
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth())
}

@Composable
fun NamesClient(){
    var value by remember { mutableStateOf("") }
    var stateField by remember { mutableStateOf(0) }
    OutlinedTextField(value = value,
        onValueChange = {value = it},
        trailingIcon = {
            when(stateField){
                1 -> {
                    Icon(imageVector = Icons.Default.Info, contentDescription = stringResource(id = R.string.desc_icon_field_text))
                }
                2 -> {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = stringResource(id = R.string.desc_icon_field_text))
                }
            }
        },
        label = { Text(text = stringResource(id = R.string.ft_name)) },
        singleLine = true,
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )
}

@Composable
fun SurnamesClient(){
    var value by remember { mutableStateOf("") }
    OutlinedTextField(value = value,
        onValueChange = { value = it },
        label = { Text(text = stringResource(id = R.string.ft_surname)) },
        singleLine = true,
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )
}

@Composable
fun BrandCar(){
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Alfa Romeo", "Audi", "BMW", "Chevrolet", "Citroën", "Fiat", "Ford", "Honda", "Hyundai", "KIA", "Mazda", "Nissan", "Toyota", "Volkswagen")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(start = 12.dp, end = 12.dp)) {
        OutlinedTextField(
            value = selectedText,
            readOnly = true,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(stringResource(id = R.string.ft_brand)) },
            trailingIcon = {
                Icon(icon,stringResource(id = R.string.desc_icon_spinners),
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
fun ServiceType(){
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Regular", "Silver", "Premium")
    var selectedText by remember  { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(start = 12.dp, end = 12.dp)) {
        OutlinedTextField(
            value = selectedText,
            readOnly = true,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(text = stringResource(id = R.string.ft_serviceType)) },
            trailingIcon = {
                Icon(icon, stringResource(id = R.string.desc_icon_spinners),
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }

}

@Composable
fun Observations(){
    var value by remember  { mutableStateOf("") }
    OutlinedTextField(value = value,
        onValueChange = {
            value = it},
        label = { Text(text = stringResource(id = R.string.ft_observation)) },
        singleLine = false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 24.dp)
            .height(120.dp))
}

@Preview(showSystemUi = true)
@Composable
fun PreviewFormService(){
    ServiceFormScreen()
}