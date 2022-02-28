package com.example.carwashapp.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carwashapp.repositories.Result
import com.example.carwashapp.repositories.ServiceRepository
import com.example.carwashapp.utils.ServiceListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ServiceListViewModel
@Inject
constructor(
    private val serviceRepository: ServiceRepository
): ViewModel(){
    private val _state: MutableState<ServiceListState> = mutableStateOf(ServiceListState())
    val state: State<ServiceListState> get() = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing : StateFlow<Boolean> = _isRefreshing

    init {
        getServiceList()
    }

    fun getServiceList(){
        serviceRepository.getServicesList().onEach { result ->
            when(result){
                is Result.Error -> {
                    _state.value = ServiceListState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = ServiceListState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = ServiceListState(services = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

}