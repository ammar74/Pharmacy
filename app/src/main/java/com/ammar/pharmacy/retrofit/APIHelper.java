    package com.ammar.pharmacy.retrofit;

    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;

    public class APIHelper {
    public static NetworkAPI api;

    public APIHelper ()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.10.213:3000/")
                .addConverterFactory(GsonConverterFactory.create())
              // .client(client)
                .build();
        api = retrofit.create(NetworkAPI.class);
    }

}
