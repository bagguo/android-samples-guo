@file:Suppress("ktlint:standard:filename")

package com.example.guo.designpattern.delegation

interface SoundBehavior { // 1
    fun makeSound()
}

class ScreamBehavior(
    val n: String,
) : SoundBehavior { // 2
    override fun makeSound() = println("${n.uppercase()} !!!")
}

class RockAndRollBehavior(
    val n: String,
) : SoundBehavior { // 2
    override fun makeSound() = println("I'm The King of Rock 'N' Roll: $n")
}

// Tom Array is the "singer" of Slayer
class TomArray(
    n: String,
) : SoundBehavior by ScreamBehavior(n) // 3

// You should know ;)
class ElvisPresley(
    n: String,
) : SoundBehavior by RockAndRollBehavior(n) // 3

fun main() {
    val tomArray = TomArray("Thrash Metal")
    tomArray.makeSound() // 4
    val elvisPresley = ElvisPresley("Dancin' to the Jailhouse Rock.")
    elvisPresley.makeSound()
}
