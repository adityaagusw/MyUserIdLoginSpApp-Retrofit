package com.example.crudlogin.BackEnd;

import com.example.crudlogin.Model.PesanModel;
import com.example.crudlogin.Model.ResponseApiModel;
import com.example.crudlogin.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseApiModel> loginUser(@Field("username") String nim,
                                     @Field("password") String nama);

    @FormUrlEncoded
    @POST("kirimPesan.php")
    Call<PesanModel> kirimPesan(@Field("user_id") String user_id,
                                @Field("pesan") String pesan);


}
