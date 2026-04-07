package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

// TODO (06) Change RecyclerView.Adapter’s parameter to <SleepNightAdapter.ViewHolder>.
class SleepNightAdapter : RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    // TODO (08) Change onBindViewHolder’s holder parameter type to ViewHolder, and update views.
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()

        if (item.sleepQuality <= 1) {
            holder.textView.setTextColor(Color.RED) // red
        } else {
            // reset
            holder.textView.setTextColor(Color.BLACK) // black
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        // TODO (07) Update view to inflate list_item_sleep_night, and change
        // return type to ViewHolder.

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
                .inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }

    // TODO (04) Create a ViewHolder class that extends RecyclerView.ViewHolder.

    // TODO (05) Inside the ViewHolder, use findViewById() to create properties for sleepLength,
    // quality, and qualityImage.

}
