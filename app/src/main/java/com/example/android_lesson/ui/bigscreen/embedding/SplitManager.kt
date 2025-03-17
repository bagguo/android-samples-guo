package com.example.android_lesson.ui.bigscreen.embedding

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.window.embedding.ActivityFilter
import androidx.window.embedding.ActivityRule
import androidx.window.embedding.RuleController
import androidx.window.embedding.SplitAttributes
import androidx.window.embedding.SplitPairFilter
import androidx.window.embedding.SplitPairRule
import androidx.window.embedding.SplitPlaceholderRule
import androidx.window.embedding.SplitRule

/**
 * @title
 * @author Darren.eth
 */
class SplitManager {
    companion object {
        fun createSplit(context: Context) {
            val splitPairFilter = SplitPairFilter(
                ComponentName(context, SplitListActivity::class.java),
                ComponentName(context, DetailActivity::class.java),
                null
            )

            val filterSet = setOf(splitPairFilter)

            val splitAttributes: SplitAttributes = SplitAttributes.Builder()
                .setSplitType(SplitAttributes.SplitType.ratio(0.33f))
                .setLayoutDirection(SplitAttributes.LayoutDirection.LEFT_TO_RIGHT)
                .build()

            val splitPairRule = SplitPairRule.Builder(filterSet)
                .setDefaultSplitAttributes(splitAttributes)
                .setMinWidthDp(840)
                .setMinSmallestWidthDp(600)
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