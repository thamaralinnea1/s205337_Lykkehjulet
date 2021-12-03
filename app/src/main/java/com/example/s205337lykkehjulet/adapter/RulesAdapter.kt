package com.example.s205337lykkehjulet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s205337lykkehjulet.R

class RulesAdapter (private val ruleList: List<String> ): RecyclerView.Adapter<RulesAdapter.RulesViewHolder> () {
    class RulesViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val ruleText: TextView = view.findViewById(R.id.textRule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.listofrules, parent,false)
        return RulesViewHolder(adapterLayout)

          }

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        holder.ruleText.text = ruleList[position]
    }

    override fun getItemCount(): Int {
        return ruleList.size
    }

}