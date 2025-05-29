package com.example.guo.ui.recyclerview.nested

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.guo.R

/**
 * @title
 * @author Darren.eth
 */
class Level0Adapter(val list: List<Level0Model>) : Adapter<Level0Adapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.tv)
        val rv = view.findViewById<RecyclerView>(R.id.rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.item_level0, parent, false)
        return VH(layout.rootView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val level0Model = list[position]
        holder.tv.text = "level 0, index:" + level0Model.index.toString()

        // 两种adapter
        // 1.原生adapter
//        val level1Adapter = Level1Adapter(level0Model.level1s)
//        holder.rv.adapter = level1Adapter

        // 2.BaseQuickAdapter
        val level1Adapter = Level1AdapterV2()
        holder.rv.adapter = level1Adapter
        level1Adapter.submitList(level0Model.level1s)

        holder.rv.postDelayed({
            val icon1 = "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=e9873bfca944ad342eea8f81e09220cc/a8ec8a13632762d08fa73daea8ec08fa513dc602.jpg"

            level0Model.level1s.add(Level1Model(99, icon1))
            // notify和submitList都不会闪动
//            holder.rv.adapter?.notifyDataSetChanged()
            level1Adapter.submitList(level0Model.level1s)

        }, 3000)
    }
}