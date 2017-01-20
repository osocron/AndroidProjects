package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import java.util.concurrent.TimeUnit

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import siscomp.osocron.com.ordencompras.model.entities.Cliente
import siscomp.osocron.com.ordencompras.model.json.Empresas
import siscomp.osocron.com.ordencompras.model.json.Mensaje

interface RestAPI {

    @GET("api/clientes")
    fun clientes(): Call<List<Cliente>>

    @GET("api/empresas")
    fun empresas(): Call<List<Empresas>>

    @FormUrlEncoded
    @POST("api/login")
    fun login(@Field("empresa") empresa: String,
              @Field("clave") clave: String,
              @Field("nombre") nombre: String) : Call<Mensaje>

    object Factory {
        fun getIstance(context: Context): RestAPI {
            val service: RestAPI
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(15, TimeUnit.SECONDS)
            builder.connectTimeout(10, TimeUnit.SECONDS)
            builder.writeTimeout(10, TimeUnit.SECONDS)

            val cacheSize = 10 * 1024 * 1024 // 10 MiB
            val cache = Cache(context.cacheDir, cacheSize.toLong())
            builder.cache(cache)

            val retrofit = Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL).build()
            service = retrofit.create(RestAPI::class.java)
            return service
        }
    }

    companion object {
        val BASE_URL = "http://192.168.1.69:9000/"
    }


}