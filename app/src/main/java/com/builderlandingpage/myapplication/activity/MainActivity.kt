package com.builderlandingpage.myapplication.activity

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.builderlandingpage.myapplication.R
import com.builderlandingpage.myapplication.adapter.PlanAdapter
import com.builderlandingpage.myapplication.data.model.MembershipPacakge
import com.builderlandingpage.myapplication.data.requestBody.PlanRequest
import com.builderlandingpage.myapplication.data.viewModel.PlanViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity(),PlanAdapter.DataClick {

    lateinit var planViewModel: PlanViewModel
    lateinit var recycler:RecyclerView
    lateinit var planAdapter: PlanAdapter
    lateinit var imgLogo:ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recy_data)
        imgLogo = findViewById(R.id.img_logo)

        displayImageProfile(this,imgLogo,"https://cdn-icons-png.flaticon.com/512/25/25231.png")

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
                planAdapter= PlanAdapter(response.packageX,this)
                recycler.adapter=planAdapter
            }

        })
    }

    fun displayImageProfile(ctx: Context?, img: ImageView?, url: String?) {
        if (url != null && url.trim { it <= ' ' }.length > 1) {
            try {
                Glide.with(ctx!!).load(url).apply(
                    RequestOptions().placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                    .into(img!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun ItemClick(pacakge: MembershipPacakge.Package?) {
        Toast.makeText(this, "You Click " + pacakge?.packageName, Toast.LENGTH_SHORT).show()
    }

}