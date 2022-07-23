package com.example.wildfireslive.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wildfireslive.db.entities.SavedLocation
import com.example.wildfireslive.repositories.SavedLocationsRepository
import kotlinx.coroutines.launch

class SavedLocationsViewModel(context: Context) : BaseViewModel() {

    private val TAG = "SavedLocationsViewModel"
    private val savedLocationsRepository: SavedLocationsRepository

//    private lateinit var _savedLocations: MutableLiveData<List<SavedLocation>>
    lateinit var savedLocations: LiveData<List<SavedLocation>>
//        get() = _savedLocations


    init {
        SavedLocationsRepository.context = context
        savedLocationsRepository = SavedLocationsRepository()
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            //val loc = SavedLocation(0, "Larissa", LatLng(50.00, 50.00), LocalDate.now(), false, false)
            //val loc2 = SavedLocation(0, "Trikala", LatLng(50.00, 50.00), LocalDate.now(), false, false)

//            val savedLocationList = savedLocationsRepository.getAllSavedLocationsSortedById().value
//            Log.v(TAG, "${savedLocationList}")
            savedLocations = savedLocationsRepository.getAllSavedLocationsSortedByCity()
            Log.v(TAG, "$savedLocations.value")

        }
    }

    fun insertSavedLocation(location: SavedLocation) {
        viewModelScope.launch {
            savedLocationsRepository.insertSavedLocation(location)
        }
    }

    fun deleteSavedLocation(location: SavedLocation) {
        viewModelScope.launch {
            savedLocationsRepository.deleteSavedLocation(location)
        }
    }


}