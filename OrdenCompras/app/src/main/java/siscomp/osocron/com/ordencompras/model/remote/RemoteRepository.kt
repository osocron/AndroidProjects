package siscomp.osocron.com.ordencompras.model.remote

import retrofit2.Call


interface RemoteRepository<T, I> {

    val getAllCall: Call<List<T>>

    fun getAll(): List<T>? {
        return callAction(getAllCall)
    }

    fun getById(id: I): T?

    fun callAction(call: Call<List<T>>): List<T>? {
        try {
            return call.execute().body()
        } catch (e: Exception) {
            return null
        }
    }

}