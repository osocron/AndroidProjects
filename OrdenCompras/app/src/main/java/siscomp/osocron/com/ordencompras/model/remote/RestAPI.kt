package siscomp.osocron.com.ordencompras.model.remote

import android.content.Context
import java.util.concurrent.TimeUnit

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import siscomp.osocron.com.ordencompras.model.json.*

interface RestAPI {

    @GET("api/clientes")
    fun clientes(): Call<List<JsonCliente>>

    @GET("api/clientes/nuevos/{fecha}")
    fun nuevosClientes(@Path("fecha") fecha: String): Call<List<JsonCliente>>

    @GET("api/empresas")
    fun empresas(): Call<List<JsonEmpresas>>

    @GET("api/existencias/{clave}")
    fun existencias(@Path("clave") clave: String): Call<JsonExistenc>

    @GET("api/existencias/{clave}/{subclave}")
    fun existenciasDetallad(@Path("clave") clave: String,
                            @Path("subclave") subclave: String): Call<JsonExistenc>

    @GET("api/articulos")
    fun articulos(): Call<List<JsonArticulo>>

    @GET("api/articulos/nuevos/{fecha}")
    fun nuevosArticulos(@Path("fecha") fecha: String): Call<List<JsonArticulo>>

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
