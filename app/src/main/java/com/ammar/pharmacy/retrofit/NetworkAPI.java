package com.ammar.pharmacy.retrofit;

import com.ammar.pharmacy.PharmacyCheck;
import com.ammar.pharmacy.PharmacyRespond;
import com.ammar.pharmacy.login.LoginObject;
import com.ammar.pharmacy.login.LoginReturnBody;
import com.ammar.pharmacy.register.RegisterObject;
import com.ammar.pharmacy.register.RegisterReturnBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkAPI {
    @POST("pharmacySignin")
    Call<LoginReturnBody> Login(@Body LoginObject loginObject);

    @POST("pharmacySignup")
    Call<RegisterReturnBody> Register(@Body RegisterObject registerObject);

    @POST("pharmacyAgree")
    Call<PharmacyRespond> Pharmacyagree(@Body PharmacyCheck pharmacyCheck);

    @GET("pharmacyNotAgree")
    Call<PharmacyRespond> Pharmacynotagree(@Body PharmacyCheck pharmacyCheck);

}
