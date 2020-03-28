package id.lacakcepat.covidnineteen.data.source.remote.model.request

data class Register(
    val fullname: String,
    val email: String,
    val password: String,
    val phone_number: String
)