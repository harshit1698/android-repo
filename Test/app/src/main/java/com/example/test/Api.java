package com.example.test;
import android.database.Observable;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
/*

    @GET("user-allocated/")
    Call<Allocated> get_allocated(@Query("r_id")int id);

    @GET("get-curr-stock-user/")
    Call<List<user_Stock>> get_user_stock(@Query("shop_id")int id,@Query("ration_id")int ration_id);

    @GET("get-curr-stock-shop/")
    Call<List<user_Stock>> get_curr_stock(@Query("shop_id")int id);



//    @FormUrlEncoded
    @POST("test/")
    Call<Otp_Model> get_test(@Body Array_Transfer at);


    @POST("add-stock-user/")
    Call<ResponseBody> confirm_order(@Body Order_Array array);


    @GET("show-family-members/")
    Call<List<ModelForFamilyMem>> get_family(@Query("ration_id") int r_id);

    @GET("get-orders/")
    Call<List<Order_Model>> get_orders(@Query("ration_id") int r_id);

    @GET("order-detail/")
    Call<List<Order_detail_model>> get_order_detail(@Query("order_id") int id);


*/
//@Part("other") RequestBody other
    @Multipart
    @POST("im/")
    Call<ResponseBody> updateProfile(
                                           @Part("full_name") RequestBody fullName,
                                           @Part MultipartBody.Part image
                                           );
}
