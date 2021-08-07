package com.example.ru2good2go

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PlaceViewModel : ViewModel() {
    //the internal MutableLiveData string that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    //the external immutable LiveData for the request status string
    val status: LiveData<String>
        get() = _status

    private val _place = MutableLiveData<PlaceWithID>()
    val place: LiveData<PlaceWithID>
        get() = _place



    //call getPlaceInfo() on init so we can display status immediately
    init {
        getPlaceID()
    }

    private fun getPlaceID() {
        viewModelScope.launch {
            try {
                var listResult = PlacesAPI.retrofitService.getPlace()
                _status.value = "Success: ${listResult.size} places found"
                if(listResult.size > 0) {
                    _place.value = listResult[0]
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

}