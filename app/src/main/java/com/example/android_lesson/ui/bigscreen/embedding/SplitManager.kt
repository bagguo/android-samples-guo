package com.example.android_lesson.ui.bigscreen.embedding

import android.content.ComponentName
import android.content.Intent
import androidx.window.embedding.ActivityFilter
import androidx.window.embedding.ActivityRule
import androidx.window.embedding.RuleController
import androidx.window.embedding.SplitAttributes
import androidx.window.embedding.SplitPairFilter
import androidx.window.embedding.SplitPairRule
import androidx.window.embedding.SplitPlaceholderRule
import androidx.window.embedding.SplitRule
import com.example.android_lesson.App

/**
 * @title
 * @author Darren.eth
 */
class SplitManager {
    companion object {
        private const val TAG = "SplitManager"

        fun createSplit() {
            val context = App.mContext

            // 创建一个分屏对过滤器，将 ListActivity 和 DetailActivity 标识为共享分屏的 activity：
            val splitPairFilter = SplitPairFilter(
                ComponentName(context, SplitListActivity::class.java),
                ComponentName(context, DetailActivity::class.java),
                null // 用于启动辅助 activity 的 intent 操作. 如果您添加了 intent 操作，过滤器会检查该操作以及 activity 名称。对于您自己应用中的 activity，您可能不需要过滤 intent 操作，因此该参数可以为 null。
            )

            val filterSet = setOf(splitPairFilter)

            // 为分屏创建布局属性：
            val splitAttributes: SplitAttributes = SplitAttributes.Builder()
                .setSplitType(SplitAttributes.SplitType.ratio(0.33f)) // 定义将可用显示区域分配给每个 activity 容器的方式。宽高比分屏类型指定主要容器占据的显示区域比例；辅助容器则会占据剩余的显示区域。
                .setLayoutDirection(SplitAttributes.LayoutDirection.LEFT_TO_RIGHT) // 指定 activity 容器相对于另一种容器的布局方式，主要容器优先。
                .build()

            // 构建分屏对规则：
            // filterSet: 包含 activity 过滤条件，通过确定共享分屏的 activity 以确定何时应用规则。在示例应用中，ListActivity 和 DetailActivity 在分屏对过滤器中指定（
            val splitPairRule = SplitPairRule.Builder(filterSet)
                .setDefaultSplitAttributes(splitAttributes)
                .setMinWidthDp(840) // 指定两个 activity 在屏幕上同时显示所需的最小显示宽度 (840)。单位是密度无关像素 (dp)
                .setMinSmallestWidthDp(600) // 设置应使用分割时父窗口的最小宽度值（以 DP 为单位）。当窗口大小小于此处请求的大小时，辅助容器中的活动将堆叠在主容器中的活动之上，与主容器中的活动完全重叠。SPLIT_MIN_DIMENSION_DP_DEFAULT如果应用程序未设置，则默认SPLIT_MIN_DIMENSION_ALWAYS_ALLOW为始终允许拆分。
                .setFinishPrimaryWithSecondary(SplitRule.FinishBehavior.NEVER)
                .setFinishSecondaryWithPrimary(SplitRule.FinishBehavior.ALWAYS)
                .setClearTop(false)
                .build()

            val ruleController = RuleController.getInstance(context)
            ruleController.addRule(splitPairRule)

            val placeholderActivityFilter = ActivityFilter(
                ComponentName(context, SplitListActivity::class.java),
                null
            )

            val placeholderActivityFilterSet = setOf(placeholderActivityFilter)

            val splitPlaceholderRule = SplitPlaceholderRule.Builder(
                placeholderActivityFilterSet,
                Intent(context, PlaceholderActivity::class.java)
            ).setDefaultSplitAttributes(splitAttributes)
                .setMinWidthDp(840)
                .setMinSmallestWidthDp(600)
                .setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior.ALWAYS)
                .build()

            ruleController.addRule(splitPlaceholderRule)

            val summaryActivityFilter = ActivityFilter(
                ComponentName(context, SummaryActivity::class.java),
                null
            )

            val summaryActivityFilterSet = setOf(summaryActivityFilter)

            val activityRule = ActivityRule.Builder(summaryActivityFilterSet)
                .setAlwaysExpand(true)
                .build()
            ruleController.addRule(activityRule)

        }
    }

}