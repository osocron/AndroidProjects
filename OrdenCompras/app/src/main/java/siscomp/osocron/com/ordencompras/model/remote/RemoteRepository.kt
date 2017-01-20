package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context

/**
 * Created by osocron on 19/01/17.
 */
interface RemoteRepository<T, I> {

    fun getAll(): List<T>?

    fun getById(id: I): T?

}