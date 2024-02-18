package com.example.androidpractice1.tricks

object Trickier {
    @JvmStatic
    fun main(args: Array<String>) {
        val cats = listOf(
            Cat(".Luna.", "image1.png"),
            Cat(".Silla.", "image2.png"),
            Cat(".Nancy.", "image3.png")
        )
        val removedPngs = cats.map {
            it.image.removeSuffix(".png")
        }
        // [image1, image2, image3]
        val removedDots = cats.map {
            it.name.removeSurrounding(".")
        }
        // [Luna, Silla, Nancy]
    }

    data class Cat(val name: String, val image: String)
}