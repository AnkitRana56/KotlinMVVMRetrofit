package com.builderlandingpage.myapplication.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.builderlandingpage.myapplication.data.model.MembershipPacakge
import com.builderlandingpage.myapplication.data.repository.PlanRepository
import com.builderlandingpage.myapplication.data.requestBody.PlanRequest

class PlanViewModel : ViewModel(){
    var planLiveData :MutableLiveData<MembershipPacakge>?=null

    fun getPlanData(planRequest: PlanRequest) :LiveData<MembershipPacakge>?{
        planLiveData=PlanRepository.getMemberPlan(planRequest)
        return planLiveData
    }


}