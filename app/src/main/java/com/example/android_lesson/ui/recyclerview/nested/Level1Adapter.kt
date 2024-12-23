package com.example.android_lesson.ui.recyclerview.nested

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.android_lesson.R

/**
 * @title
 * @author Darren.eth
 */
class Level1Adapter(val level1s: List<Level1Model>) : Adapter<Level1Adapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.iv)
        val tv: TextView = view.findViewById(R.id.tv)

        fun bind(item:Level1Model) {
            tv.text = "level 1, index:" + item.index.toString()
            Glide.with(iv.context)
                .load(item.icon)
                .into(iv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = layoutInflater.inflate(R.layout.item_level1, parent, false)
        return VH(layout.rootView)
    }

    override fun getItemCount(): Int {
        return level1s.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(level1s[position])
    }
}