package network

import android.content.res.Resources
import android.provider.Settings.Global.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ru2good2go.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//FOLLOWING RUMAD RECORDING
class PlaceViewModel : ViewModel() {
    private val _currentPlace = MutableLiveData<PlaceWithID>()
    val currentPlace: LiveData<PlaceWithID>
        get() = _currentPlace

    fun showPlace(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val place = PlacesAPI.retrofitService.getPlace(Resources.getSystem().getString(R.string.google_services_api_key), input, "textquery")
            _currentPlace.postValue(place)
        }
    }
}





