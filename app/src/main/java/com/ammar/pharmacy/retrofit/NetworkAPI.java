package com.ammar.pharmacy.retrofit;

import com.ammar.pharmacy.PharmacyCheck;
import com.ammar.pharmacy.PharmacyRespond;
import com.ammar.pharmacy.orders.GetOrdersObject;
import com.ammar.pharmacy.orders.GetOrdersReturnBody;
import com.ammar.pharmacy.login.LoginObject;
import com.ammar.pharmacy.login.LoginReturnBody;
import com.ammar.pharmacy.orders.History;
import com.ammar.pharmacy.register.RegisterObject;
import com.ammar.pharmacy.register.RegisterReturnBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NetworkAPI {
    @POST("pharmacySignin")
    Call<LoginReturnBody> Login(@Body LoginObject loginObject);

    @POST("pharmacySignup")
    Call<RegisterReturnBody> register(@Body RegisterObject registerObject);

    @GET("getOrders")
    Call<GetOrdersReturnBody> getOrders(@Header("token")String token );

    @GET("medicalhistoryRetrieve")
    Call<History> medicalHistoryRetrieve(@Header("token")String token);


    @POST("pharmacyAgree")
    Call<PharmacyRespond> PharmacyAgree(@Body PharmacyCheck pharmacyCheck);

    @GET("pharmacyNotAgree")
    Call<PharmacyRespond> PharmacyNotAgree(@Body PharmacyCheck pharmacyCheck);




}
