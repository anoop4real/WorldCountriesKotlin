package com.example.anoopmohanan.worldcountrieskotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.countrylist_layout.view.*

/**
 * Created by anoopmohanan on 29/11/17.
 */

class CountryDataAdapter(val items: List<Country>):RecyclerView.Adapter<CountryDataAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        if (holder == null){
            return
        }
        holder.countryListCell.mainText.text = items[position].countryName
        holder.countryListCell.detailedText.text = items[position].countryCode
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent!!.context)
        val row = layoutInflater.inflate(R.layout.countrylist_layout,parent,false)

        return ViewHolder(row)
    }


    class ViewHolder(val countryListCell: View): RecyclerView.ViewHolder(countryListCell){

    }
}