package id.lacakcepat.covidnineteen.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.lacakcepat.covidnineteen.data.source.remote.model.User
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.RegisterResponse
import id.lacakcepat.covidnineteen.data.source.repository.LacakCepatRepository
import id.lacakcepat.covidnineteen.data.source.repository.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val repository: LacakCepatRepository) :
    ViewModel() {

    private val _isVisibleProgressBar = MutableLiveData<Boolean>()
    val isVisibleProgressBar: LiveData<Boolean>
        get() = _isVisibleProgressBar

    private val _isVisibleErrorName = MutableLiveData<Boolean>()
    val isVisibleErrorName: LiveData<Boolean>
        get() = _isVisibleErrorName

    private val _isVisibleErrorPhone = MutableLiveData<Boolean>()
    val isVisibleErrorPhone: LiveData<Boolean>
        get() = _isVisibleErrorPhone

    private val _isEnableToRegister = MutableLiveData<Boolean>()
    val isEnableToRegister: LiveData<Boolean>
        get() = _isEnableToRegister

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse>
        get() = _registerResponse

    fun validateName(text: CharSequence?) {
        _isVisibleErrorName.value = text?.let { it.trim().length < 4 }
        _isEnableToRegister.value = isEnableToRegister()
    }

    fun validatePhone(text: CharSequence?) {
        _isVisibleErrorPhone.value = text?.let {
            it.contains("+") ||
                    it.indexOf("0") == 0 ||
                    it.trim().length < 8 ||
                    !Patterns.PHONE.matcher(it.trim()).matches()
        }
        _isEnableToRegister.value = isEnableToRegister()
    }

    fun doRegister(user: User) {

        val map = mutableMapOf<String, Any?>(
            "fullname" to user.fullName,
            "phone_number" to "+62${user.phoneNumber}"
        )

        _isVisibleProgressBar.value = true
        GlobalScope.launch {

            when (val response = repository.registerUser(map)) {

                is Result.Success -> {
                    _isVisibleProgressBar.postValue(false)
                    _registerResponse.postValue(response.data)
                }

                is Result.Error -> {
                    _isVisibleProgressBar.postValue(false)
                    _registerResponse.postValue(null)

                    response.exception?.printStackTrace()
                }

            }

        }

    }

    private fun isEnableToRegister() =
        _isVisibleErrorName.value.let { it != null && !it } && _isVisibleErrorPhone.value.let { it != null && !it }

}