package com.example.william.learnrxkotlin2x.widget

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.william.learnrxkotlin2x.R
import com.example.william.learnrxkotlin2x.pojo.FunPojo

/**
 * Created by william on 2018/2/14.
 */

/**
 *
 */
class OperatorFunAdapter(val pojoList: ArrayList<FunPojo>,val listener:OnItemClickListener) : RecyclerView.Adapter<OperatorFunAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return pojoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperatorFunAdapter.ViewHolder {
        val tv = LayoutInflater.from(parent.context).inflate(R.layout.item_title_func,parent,false)
        return OperatorFunAdapter.ViewHolder(tv as TextView)
    }

    override fun onBindViewHolder(holder: OperatorFunAdapter.ViewHolder, position: Int) {
        val pojo = pojoList.get(position)
        holder.tv.setText(pojo.name)
        holder.tv.setOnClickListener {
            listener.onItemClick(pojo.func)
        }
    }

    class ViewHolder(val tv: TextView) : RecyclerView.ViewHolder(tv)

    interface OnItemClickListener{
        fun onItemClick(func:String)
    }
}
