package com.example.guo.language.kotlin.instance

/**
 * @title
 * @author Darren.eth
 */
class InstanceSample {

    companion object {
        val instance by lazy { InstanceSample() }
    }
}