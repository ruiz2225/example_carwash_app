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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.carwashapp.R
import com.example.carwashapp.ui.theme.GreenOk
import com.example.carwashapp.utils.ServiceDetailState


@Composable
fun ServiceFormScreen(
    navController: NavController,
    state: ServiceDetailState,
    addNewService: (String, Int, String, String, String, String, String) -> Unit,
    updateService: (String, Int, String, String, String, String, String) -> Unit){
    Scaffold(topBar = {
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.desc_icon_button),
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(start = 16.dp))
            Text(text = stringResource(id = R.string.text_topBar),
                modifier = Modifier.padding(start = 32.dp))
        }
    }) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            FormService(state)
            if(state.service?.id != null){
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(48.dp), onClick = { updateService(plate, Integer.parseInt(identification), names, surnames, brand, serviceType, observation) }) {
                    Icon(modifier = Modifier.padding(end = 8.dp), imageVector = Icons.Filled.Edit, contentDescription = "Send Icon")
                    Text(text = stringResource(id = R.string.btn_updateService))
                }
            }else{
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(48.dp), onClick = { addNewService(plate, Integer.parseInt(identification), names, surnames, brand, serviceType, observation) }) {
                    Icon(modifier = Modifier.padding(end = 8.dp), imageVector = Icons.Filled.Send, contentDescription = "Send Icon")
                    Text(text = stringResource(id = R.string.btn_newService))
                }
            }
        }
    }

}

private var plate: String = ""
private var identification: String = ""
private var names: String = ""
private var surnames: String = ""
private var brand: String = ""
private var serviceType: String = ""
private var observation: String = ""


@Composable
fun FormService(state: ServiceDetailState){
    var id = ""
    if(state.service?.identificationClient != null)
        id = state.service.identificationClient.toString()
    TitleNewService()
    Plate(state)
    IdentificationClient(id)
    NamesClient(state)
    SurnamesClient(state)
    BrandCar(state)
    ServiceType(state)
    Observations(state)
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
fun Plate(state: ServiceDetailState){
    var value by remember (state.service?.plate) { mutableStateOf(state.service?.plate ?: "")}
    plate = value
    val maxCharacter = 6
    var stateField by remember { mutableStateOf(0) }
    var isError by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = {
            if(it.length <= maxCharacter){
                value = it
                plate = value
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
fun IdentificationClient(id: String){
    var value by remember (id) { mutableStateOf(id)}
    identification = value
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
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(id = R.string.desc_icon_field_text))
                }
                2 -> {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = stringResource(id = R.string.desc_icon_field_text),
                        tint = GreenOk)
                }
            }
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(id = R.string.ft_identification)) },
        singleLine = true,
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth())
}

@Composable
fun NamesClient(state: ServiceDetailState){
    var value by remember (state.service?.namesClient) { mutableStateOf(state.service?.namesClient ?: "")}
    names = value
    val minCharacter = 3
    var stateField by remember { mutableStateOf(0) }
    var isError by remember { mutableStateOf(false) }
    OutlinedTextField(value = value,
        onValueChange = {
                value = it
                names = value
            if(it.length > 0 && it.length < minCharacter){
                stateField = 1
                isError = it.length < minCharacter
            }else{
                stateField = 2
                isError = false
            }},
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
        label = { Text(text = stringResource(id = R.string.ft_name)) },
        singleLine = true,
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )
}

@Composable
fun SurnamesClient(state: ServiceDetailState){
    var value by remember (state.service?.surnamesClient) { mutableStateOf(state.service?.surnamesClient ?: "")}
    surnames = value
    val minCharacter = 3
    var stateField by remember { mutableStateOf(0) }
    var isError by remember { mutableStateOf(false) }
    OutlinedTextField(value = value,
        onValueChange = {
            value = it
            surnames = value
            if(it.length > 0 && it.length < minCharacter){
                stateField = 1
                isError = it.length < minCharacter
            }else{
                stateField = 2
                isError = false
            }},
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
        label = { Text(text = stringResource(id = R.string.ft_surname)) },
        singleLine = true,
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
    )
}

@Composable
fun BrandCar(state: ServiceDetailState){
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Alfa Romeo", "Audi", "BMW", "Chevrolet", "Citroën", "Fiat", "Ford", "Honda", "Hyundai", "KIA", "Mazda", "Nissan", "Toyota", "Volkswagen")
    var selectedText by remember (state.service?.brand) { mutableStateOf(state.service?.brand ?: "")}
    brand = selectedText
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
fun ServiceType(state: ServiceDetailState){
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Regular", "Silver", "Premium")
    var selectedText by remember (state.service?.serviceType) { mutableStateOf(state.service?.serviceType ?: "")}
    serviceType = selectedText

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
fun Observations(state: ServiceDetailState){
    var value by remember (state.service?.observations) { mutableStateOf(state.service?.observations ?: "")}
    observation = value
    OutlinedTextField(value = value,
        onValueChange = {
            value = it
            observation = value},
        label = { Text(text = stringResource(id = R.string.ft_observation)) },
        singleLine = false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 24.dp)
            .height(120.dp))
}

private fun isValid():Boolean{
    var isValidForm = false
    if(plate.length == 6 && identification.length >= 6 && names.length > 3 && surnames.length > 3 && brand.isNotBlank() && serviceType.isNotBlank())
        isValidForm = true

    return isValidForm
}