package com.builderlandingpage.myapplication.api

import com.builderlandingpage.myapplication.data.model.MembershipPacakge
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("registration_details_controller.php")
    fun GetPlan(
        @Field("getMemberPlan") tag: String?,
        @Field("society_id") society_id: String?,
        @Field("language_id") language_id: String?,
        @Field("member_category_id") member_category_id: String?,
    ): Call<MembershipPacakge?>?
}