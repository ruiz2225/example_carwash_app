package com.example.carwashapp.repositories

import com.example.carwashapp.model.Service
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRepository
@Inject
constructor(
    private val serviceList: CollectionReference
){
    //Para agregar nuevos datos de nuestro modelo servicio a la colección de Firestore
    fun addNewService(service: Service){
        try{
            serviceList.document(service.id).set(service)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    //Para obtener todas los servicios de Firestore
    fun getServicesList(): Flow<Result<List<Service>>> = flow{
        try {
            emit(Result.Loading<List<Service>>())
            val serviceList = serviceList.get().await().map { document ->
                document.toObject(Service::class.java)
            }
            emit(Result.Success<List<Service>>(data = serviceList))
        }catch (e:Exception){
            emit(Result.Error<List<Service>>(message = e.localizedMessage ?: "Error con Firebase"))
        }
    }

    //Para obtener los servicios por su ID
    fun getServiceById(id: String): Flow<Result<Service>> = flow{
        try {
            emit(Result.Loading<Service>())

            val service = serviceList
                .whereGreaterThanOrEqualTo("id", id)
                .get()
                .await()
                .toObjects(Service::class.java)
                .first()

            emit(Result.Success<Service>(data = service))
        }catch (e:Exception){
            emit(Result.Error<Service>(message = e.localizedMessage ?: "Error con Firebase"))
        }
    }

    //Para actualizar el servicio
    fun updateService(serviceId:String, serviceCarWash: Service){
        try {
            val map = mapOf(
                "plate" to serviceCarWash.plate,
                "identificationClient" to serviceCarWash.identificationClient,
                "namesClient" to serviceCarWash.namesClient,
                "surnamesClient" to serviceCarWash.surnamesClient,
                "brand" to serviceCarWash.brand,
                "serviceType" to serviceCarWash.serviceType,
                "observations" to serviceCarWash.observations
            )
            serviceList.document(serviceId).update(map)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun deleteService(serviceId: String){
        try {
            serviceList.document(serviceId).delete()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


}