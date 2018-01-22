package com.example.anoopmohanan.worldcountrieskotlin

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent!!.context)
        val row = layoutInflater.inflate(R.layout.countrylist_layout,parent,false)

        return ViewHolder(row)
    }


    class ViewHolder(countryListCell: View): RecyclerView.ViewHolder(countryListCell){

        val itemVw = countryListCell

        fun bind(item: Country) {
            itemVw.mainText.text = item.countryName
            itemVw.detailedText.text = item.countryCode
            //itemView.setOnClickListener { Toast.makeText(it.context, "$item.countryName", Toast.LENGTH_SHORT).show() }
            itemVw.setOnClickListener { loadDetails(itemView,item) }
        }

        fun loadDetails(itemView: View, item: Country){

            val context = itemView.context
            val showDetailsIntent = Intent(context, DetailActivity::class.java)
            showDetailsIntent.putExtra("CountryName", item.countryCode)
            context.startActivity(showDetailsIntent)

        }
    }
}