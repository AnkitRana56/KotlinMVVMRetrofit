package com.builderlandingpage.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.builderlandingpage.myapplication.R
import com.builderlandingpage.myapplication.adapter.PlanAdapter
import com.builderlandingpage.myapplication.data.requestBody.PlanRequest
import com.builderlandingpage.myapplication.data.viewModel.PlanViewModel

class MainActivity : AppCompatActivity() {

    lateinit var planViewModel: PlanViewModel
    lateinit var recycler:RecyclerView
    lateinit var planAdapter: PlanAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recy_data)
        recycler.layoutManager = LinearLayoutManager(this)

        planViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)
        planViewModel.planLiveData=null
        getPaln()
    }


    fun getPaln(){
        val planRequest = PlanRequest()
        planRequest.society_id="1"
        planRequest.language_id="1"

        planViewModel.getPlanData(planRequest)!!.observe(this,{
            response ->
            val msg = response.message
            if(response.status.equals("200")){
                Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show()
                planAdapter= PlanAdapter(response.packageX)
                recycler.adapter=planAdapter
            }

        })




    }

}