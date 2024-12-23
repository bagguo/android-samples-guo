package com.example.android_lesson.ui.recyclerview.nested

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_lesson.databinding.ActivityNestedRecyclerViewBinding

/**
 * RecyclerView嵌套RecyclerView
 * 不存在闪一下问题
 */
class NestedRecyclerViewActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, NestedRecyclerViewActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy {
        ActivityNestedRecyclerViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            val list = generateList()
            val level0Adapter = Level0Adapter(list)
            rv.adapter = level0Adapter

            rv.postDelayed({
                list.add(
                    Level0Model(
                        999,
                        mutableListOf(
                            Level1Model(
                                9999,
                                "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=e9873bfca944ad342eea8f81e09220cc/a8ec8a13632762d08fa73daea8ec08fa513dc602.jpg"
                            )
                        )
                    )
                )
                level0Adapter.notifyDataSetChanged()
            }, 3000)
        }
    }

    private fun generateList(): MutableList<Level0Model> {
        val list = mutableListOf<Level0Model>()

        for (i in 0..5) {
            val icon1 =
                "http://gips3.baidu.com/it/u=3886271102,3123389489&fm=3028&app=3028&f=JPEG&fmt=auto?w=1280&h=960"
            val icon2 =
                "http://gips3.baidu.com/it/u=1821127123,1149655687&fm=3028&app=3028&f=JPEG&fmt=auto?w=720&h=1280"
            val icon3 =
                "http://gips0.baidu.com/it/u=1690853528,2506870245&fm=3028&app=3028&f=JPEG&fmt=auto?w=1024&h=1024"
            val level1s = mutableListOf<Level1Model>()
            level1s.add(Level1Model(0, icon1))
            level1s.add(Level1Model(1, icon2))
            level1s.add(Level1Model(2, icon3))

            val level0 = Level0Model(i, level1s)
            list.add(level0)

        }
        return list
    }
}