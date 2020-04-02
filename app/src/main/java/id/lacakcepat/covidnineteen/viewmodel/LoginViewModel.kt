package id.lacakcepat.covidnineteen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.LoginResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.RegisterResponse
import id.lacakcepat.covidnineteen.data.source.remote.model.FormState
import id.lacakcepat.covidnineteen.data.source.repository.LacakCepatRepository
import id.lacakcepat.covidnineteen.data.source.repository.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LacakCepatRepository): ViewModel() {
    var fragmentSate: MutableLiveData<Int> = MutableLiveData()

    var loginData: MutableLiveData<Result<LoginResponse?>> = MutableLiveData()
    var registerData: MutableLiveData<Result<RegisterResponse?>> = MutableLiveData()
    var formState: MutableLiveData<FormState> = MutableLiveData()

    fun loginUser(phoneNumber: String) {
        if(!isPhoneValid(phoneNumber)) return

        viewModelScope.launch {
            val loginResource = async { repository.loginUser("0$phoneNumber") }
            loginData.postValue(loginResource.await())
        }
    }

    fun registerUser(name: String, phoneNumber: String) {
        if(!isNameValid(name) || !isPhoneValid(phoneNumber)) return

        viewModelScope.launch {
            val registerResource = async { repository.registerUser(name, "0$phoneNumber") }
            registerData.postValue(registerResource.await())
        }
    }

    fun loginDataChanged(phone: String) {
        if (!isPhoneValid(phone)) {
            formState.value = FormState(phoneError = R.string.phone_error)
        } else {
            formState.value = FormState(isDataValid = true)
        }
    }

    fun registerDataChanged(name: String, phone: String) {
        if (!isNameValid(name)) {
            formState.value = FormState(nameError = R.string.name_error)
        } else if (!isPhoneValid(phone)) {
            formState.value = FormState(phoneError = R.string.phone_error)
        } else {
            formState.value = FormState(isDataValid = true)
        }
    }

    private fun isNameValid(username: String): Boolean = username.isNotBlank() && username.length > 4

    private fun isPhoneValid(password: String): Boolean = password.isNotBlank() && password.length in 11..16
}