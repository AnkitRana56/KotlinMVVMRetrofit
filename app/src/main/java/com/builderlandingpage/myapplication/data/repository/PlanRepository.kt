package com.builderlandingpage.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.builderlandingpage.myapplication.api.RetrofitClient
import com.builderlandingpage.myapplication.data.model.MembershipPacakge
import com.builderlandingpage.myapplication.data.requestBody.PlanRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PlanRepository {

    val membershipPacakge = MutableLiveData<MembershipPacakge>()

    fun getMemberPlan(planRequest: PlanRequest):MutableLiveData<MembershipPacakge>{
        val call = RetrofitClient.apiInterface.GetPlan(planRequest.getMemberPlan!!,
        planRequest.society_id!!,planRequest.language_id,planRequest.member_category_id!!)
        call?.enqueue(object : Callback<MembershipPacakge?>{
            override fun onResponse(
                call: Call<MembershipPacakge?>,
                response: Response<MembershipPacakge?>
            ) {

                val data = response.body()
                membershipPacakge.value = MembershipPacakge(data?.message,data?.packageX,data?.status)

            }

            override fun onFailure(call: Call<MembershipPacakge?>, t: Throwable) {
               val code = call.request().body.toString()
                membershipPacakge.value= MembershipPacakge(t.message,null,code)
            }

        })
        return membershipPacakge;
    }



}