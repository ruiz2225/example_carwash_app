package com.example.carwashapp.view_model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carwashapp.model.Service
import com.example.carwashapp.repositories.Result
import com.example.carwashapp.repositories.ServiceRepository
import com.example.carwashapp.utils.ServiceDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ServiceDetailViewModel
@Inject
constructor(
    private val serviceRepository: ServiceRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _state: MutableState<ServiceDetailState> = mutableStateOf(ServiceDetailState())
    val state: State<ServiceDetailState>
        get() = _state

    init {
        savedStateHandle.get<String>("serviceId")?.let {serviceId ->
            getService(serviceId)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNewService(plate:String, identification:Int, names:String, surnames:String, brand:String, serviceType: String, observations:String){

        val serviceCarWash = Service(
            id = UUID.randomUUID().toString(),
            state = 0,
            plate = plate,
            brand = brand,
            identificationClient = identification,
            namesClient = names,
            surnamesClient = surnames,
            serviceType = serviceType,
            observations = observations,
            dateService = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(
                Instant.now())
        )

        serviceRepository.addNewService(serviceCarWash)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateService(newPlate:String, newIdentification:Int, newNames:String, newSurnames:String, newBrand:String, newServiceType: String, newObservations:String){

        if(state.value.service == null){
            _state.value = ServiceDetailState(error = "Servicio es null")
            return
        }
        val serviceEdited = state.value.service!!.copy(
            plate = newPlate,
            identificationClient = newIdentification,
            namesClient = newNames,
            surnamesClient = newSurnames,
            brand = newBrand,
            serviceType = newServiceType,
            observations = newObservations
        )

        serviceRepository.updateService(serviceEdited.id, serviceEdited)

    }

    private fun getService(serviceId: String){
        serviceRepository.getServiceById(serviceId).onEach { result ->
            when(result){
                is Result.Error -> {
                    _state.value = ServiceDetailState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = ServiceDetailState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = ServiceDetailState(service = result.data)
                }
            }

        }.launchIn(viewModelScope)
    }
}