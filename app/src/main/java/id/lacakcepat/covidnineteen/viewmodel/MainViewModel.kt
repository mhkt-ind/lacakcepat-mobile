package id.lacakcepat.covidnineteen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.ConditionsResponse
import id.lacakcepat.covidnineteen.data.source.repository.LacakCepatRepository
import id.lacakcepat.covidnineteen.data.source.repository.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: LacakCepatRepository): ViewModel() {
    var conditions: MutableLiveData<Result<ConditionsResponse?>> = MutableLiveData()

    fun sendConditions(health: String, userId: String) {
        viewModelScope.launch {
            val conditionsResource = async { repository.sendConditions(health, userId) }
            conditions.postValue(conditionsResource.await())
        }
    }
}