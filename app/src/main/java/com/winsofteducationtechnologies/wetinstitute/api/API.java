package com.winsofteducationtechnologies.wetinstitute.api;

import com.winsofteducationtechnologies.wetinstitute.model.DefaultResponse;
import com.winsofteducationtechnologies.wetinstitute.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("register")
    Call<DefaultResponse> createUser(
      @Field("name") String name ,
      @Field("email") String email ,
      @Field("password") String password ,
      @Field("gender") String gender ,
      @Field("role") String role
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
      @Field("email") String email,
      @Field("password") String password
    );
}
