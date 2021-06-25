package com.ammar.pharmacy.retrofit;

import com.ammar.pharmacy.acceptedorders.AcceptedOrdersReturn;
import com.ammar.pharmacy.acceptedorders.OrderDetailsReturn;
import com.ammar.pharmacy.acceptedorders.StatusIDWrapper;
import com.ammar.pharmacy.currentorder.DoneOrderResponse;
import com.ammar.pharmacy.currentorder.PharmacyRespond;
import com.ammar.pharmacy.currentorder.GetOrdersReturnBody;
import com.ammar.pharmacy.currentorder.IdWrapper;
import com.ammar.pharmacy.login.LoginObject;
import com.ammar.pharmacy.login.LoginReturnBody;
import com.ammar.pharmacy.more.EditNameObject;
import com.ammar.pharmacy.more.EditPasswordObject;
import com.ammar.pharmacy.ordershistory.OrderDetailsResponse;
import com.ammar.pharmacy.ordershistory.OrderHistoryResponse;
import com.ammar.pharmacy.ordershistory.PharmacyOrders;
import com.ammar.pharmacy.register.RegisterObject;
import com.ammar.pharmacy.register.RegisterReturnBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkAPI {
    @POST("pharmacySignin")
    Call<LoginReturnBody> Login(@Body LoginObject loginObject);

    @POST("pharmacySignup")
    Call<RegisterReturnBody> register(@Body RegisterObject registerObject);

    @GET("getOrders")
    Call<GetOrdersReturnBody> getOrders(@Header("token")String token );

    @POST("currentOrderPharmacy/{orderId}")
    Call<OrderDetailsResponse> orderInfo(@Path("orderId") String orderId);

    @GET("pharmacyCurrentOrders")
    Call<AcceptedOrdersReturn> acceptedOrders(@Header("token")String token);

    @GET("pharmacyOrderHistory")
    Call<OrderHistoryResponse> pharmacyOrderHistory(@Header("token")String token);

    @POST("pharmacyAgree")
    Call<PharmacyRespond> PharmacyAgree(@Header("token")String token, @Body IdWrapper idWrapper);

    @POST("pharmacyNotAgree")
    Call<PharmacyRespond> PharmacyNotAgree(@Header("token")String token,@Body IdWrapper idWrapper);

    @POST("doneorder")
    Call<DoneOrderResponse> doneOrder(@Header("token")String token,@Body StatusIDWrapper orderId);

    @POST("editPharmacyName")
    Call<PharmacyRespond> editName(@Body EditNameObject editNameObject);

    @POST("editPharmacyPass")
    Call<PharmacyRespond> editPassword (@Body EditPasswordObject editPasswordObject);


    @POST("editPharmacyPhones")
    Call<PharmacyRespond> editPhone (@Body EditPasswordObject editPasswordObject);






}
