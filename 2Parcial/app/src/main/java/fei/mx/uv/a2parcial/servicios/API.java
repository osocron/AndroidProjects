package fei.mx.uv.a2parcial.servicios;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import fei.mx.uv.a2parcial.pojos.Mensaje;
import fei.mx.uv.a2parcial.pojos.Nota;
import fei.mx.uv.a2parcial.pojos.Usuario;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    String BASE_URL = "http://192.168.1.64:8080/SegundoParcial/webresources/";

    //Metodos para Usuarios

    @FormUrlEncoded
    @POST("usuarios/login")
    Call<Usuario> login(@Field("email") String email,
                        @Field("password") String password);

    @FormUrlEncoded
    @POST("usuarios/registrarUsuario")
    Call<Mensaje> registrarUsuario(@Field("nombre") String nombre,
                                   @Field("email") String email,
                                   @Field("password") String password);

    //Metodos para Notas

    @GET("notas/notasPorUsuario/{idUsuario}")
    Call<List<Nota>> notasPorUsuario(@Path("idUsuario") Integer idUsuario);

    @FormUrlEncoded
    @POST("notas/nuevaNota")
    Call<Mensaje> nuevaNota(@Field("idusuario") Integer idUsuario,
                            @Field("nombre") String nombre,
                            @Field("descripcion") String descripcion);

    @FormUrlEncoded
    @PUT("notas/modificarNota")
    Call<Mensaje> modificarNota(@Field("idnota") Integer idNota,
                                @Field("descripcion") String descripcion);

    @FormUrlEncoded
    @PUT("notas/eliminarNota")
    Call<Mensaje> eliminarNota(@Field("idnota") Integer idNota);

    class Factory {
        private static API service;

        public static API getIstance(Context context) {
            if (service == null) {
                OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                builder.readTimeout(15, TimeUnit.SECONDS);
                builder.connectTimeout(10, TimeUnit.SECONDS);
                builder.writeTimeout(10, TimeUnit.SECONDS);

                int cacheSize = 10 * 1024 * 1024; // 10 MiB
                Cache cache = new Cache(context.getCacheDir(), cacheSize);
                builder.cache(cache);

                Retrofit retrofit = new Retrofit.Builder()
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL).build();
                service = retrofit.create(API.class);
                return service;
            } else {
                return service;
            }
        }
    }

}
