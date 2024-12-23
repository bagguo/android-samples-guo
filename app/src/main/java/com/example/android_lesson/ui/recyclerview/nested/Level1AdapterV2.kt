package com.example.android_lesson.ui.recyclerview.nested

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter4.BaseQuickAdapter
import com.example.android_lesson.databinding.ItemLevel1Binding

/**
 * @title
 * @author Darren.eth
 */
class Level1AdapterV2 : BaseQuickAdapter<Level1Model, Level1AdapterV2.VH>() {
    inner class VH(val binding: ItemLevel1Binding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item:Level1Model) {
            binding.apply {
                binding.tv.text = "level 1, index:" + item.index.toString()
                Glide.with(iv.context)
                    .load(item.icon)
                    .into(iv)
            }
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int, item: Level1Model?) {
        item?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(ItemLevel1Binding.inflate(LayoutInflater.from(context), parent, false))
    }


}