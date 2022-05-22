package com.app.wifianalyzer.wifilist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.wifianalyzer.R
import com.app.wifianalyzer.TextItemViewHolder
import com.app.wifianalyzer.database.WifiScan


class WifiListAdapter : RecyclerView.Adapter<TextItemViewHolder>() {

    var data =  listOf<WifiScan>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.wifilist_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.ssid
    }

    override fun getItemCount() = data.size

}