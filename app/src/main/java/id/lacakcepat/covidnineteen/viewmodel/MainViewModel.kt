package id.lacakcepat.covidnineteen.viewmodel

import androidx.lifecycle.ViewModel
import id.lacakcepat.covidnineteen.data.source.repository.LacakCepatRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: LacakCepatRepository): ViewModel() {

}