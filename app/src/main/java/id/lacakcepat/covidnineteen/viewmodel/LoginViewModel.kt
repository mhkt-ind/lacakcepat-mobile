package id.lacakcepat.covidnineteen.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.lacakcepat.covidnineteen.data.source.remote.model.User
import id.lacakcepat.covidnineteen.data.source.remote.model.response.lacakcepat.LoginResponse
import id.lacakcepat.covidnineteen.data.source.repository.LacakCepatRepository
import id.lacakcepat.covidnineteen.data.source.repository.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LacakCepatRepository) :
    ViewModel() {

    private val _isVisibleProgressBar = MutableLiveData<Boolean>()
    val isVisibleProgressBar: LiveData<Boolean>
        get() = _isVisibleProgressBar

    private val _isVisibleErrorPhone = MutableLiveData<Boolean>()
    val isVisibleErrorPhone: LiveData<Boolean>
        get() = _isVisibleErrorPhone

    private val _isEnableToLogin = MutableLiveData<Boolean>()
    val isEnableToLogin: LiveData<Boolean>
        get() = _isEnableToLogin

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse>
        get() = _loginResponse

    fun validatePhone(text: CharSequence?) {
        _isVisibleErrorPhone.value = text?.let {
            it.contains("+") ||
                    it.indexOf("0") == 0 ||
                    it.trim().length < 8 ||
                    !Patterns.PHONE.matcher(it.trim()).matches()
        }
        _isEnableToLogin.value = _isVisibleErrorPhone.value.let { it != null && !it }
    }

    fun doLogin(user: User) {

        _isVisibleProgressBar.value = true
        GlobalScope.launch {

            when (val response = repository.loginUser("+62${user.phoneNumber}")) {

                is Result.Success -> {
                    _isVisibleProgressBar.postValue(false)
                    _loginResponse.postValue(response.data)
                }

                is Result.Error -> {
                    _isVisibleProgressBar.postValue(false)
                    _loginResponse.postValue(null)

                    response.exception?.printStackTrace()
                }

            }

        }

    }

}