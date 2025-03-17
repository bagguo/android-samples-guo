package com.example.android_lesson.ui.bigscreen.embedding

import android.content.Context
import androidx.startup.Initializer
import androidx.window.embedding.RuleController
import com.example.android_lesson.R

/**
 * @title
 * @author Darren.eth
 */
class SplitInitializer : Initializer<RuleController> {

    override fun create(context: Context): RuleController {
        return RuleController.getInstance(context).apply {
            setRules(RuleController.parseRules(context, R.xml.main_split_config))
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return emptyList<Class<out Initializer<*>>>().toMutableList()
    }
}