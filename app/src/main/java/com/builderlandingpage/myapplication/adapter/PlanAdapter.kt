package com.builderlandingpage.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.builderlandingpage.myapplication.R
import com.builderlandingpage.myapplication.data.model.MembershipPacakge

class PlanAdapter : RecyclerView.Adapter<PlanAdapter.PlanViewHolder>{

    var pacakge:List<MembershipPacakge.Package?>?
     var dataclick:DataClick

    constructor(pacakge: List<MembershipPacakge.Package?>?,dataclick: DataClick) : super() {
        this.pacakge = pacakge
        this.dataclick=dataclick
    }

    interface DataClick{
        fun ItemClick(pacakge: MembershipPacakge.Package?)
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

        holder.itemView.setOnClickListener(View.OnClickListener {
                dataclick.ItemClick(pacakge?.get(position))
        })
    }

    override fun getItemCount(): Int {
       return pacakge?.size ?: 0
    }
}