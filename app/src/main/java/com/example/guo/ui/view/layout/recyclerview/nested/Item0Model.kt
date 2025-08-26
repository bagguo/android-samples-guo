package com.example.guo.ui.view.layout.recyclerview.nested

/**
 * @title
 * @author Darren.eth
 */
//data class ItemLevel(val level: Int)
//data class Level0Model(val level: ItemLevel)
//data class Level1Model(val level: ItemLevel)

abstract class AbsItem(open val index: Int)

data class Level0Model(
    override val index: Int,
    val level1s: MutableList<Level1Model>
) : AbsItem(index)

data class Level1Model(
    override val index: Int,
    val icon: String
) : AbsItem(index)