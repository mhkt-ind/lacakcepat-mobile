package id.lacakcepat.covidnineteen.data.source.repository

sealed class Result<T> {
    data class Success<T>(val data: T?) : Result<T>()
    data class Error<T>(val exception: Throwable?) : Result<T>()
}