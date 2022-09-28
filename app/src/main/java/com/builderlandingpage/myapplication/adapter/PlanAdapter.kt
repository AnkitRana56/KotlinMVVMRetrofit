package com.builderlandingpage.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.builderlandingpage.myapplication.R
import com.builderlandingpage.myapplication.data.model.MembershipPacakge

class PlanAdapter : RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {

    var pacakge:List<MembershipPacakge.Package?>?

    constructor(pacakge: List<MembershipPacakge.Package?>?) : super() {
        this.pacakge = pacakge
    }


    public class PlanViewHolder(itemview: View) :RecyclerView.ViewHolder(itemview){

        val txtName :TextView = itemview.findViewById(R.id.txtPlanName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan,parent,false)
        return PlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.txtName.text=pacakge?.get(position)?.packageName
    }

    override fun getItemCount(): Int {
       return pacakge?.size ?: 0
    }
}