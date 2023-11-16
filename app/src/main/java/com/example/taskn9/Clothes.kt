package com.example.taskn9

data class Clothes(
    val image: Int,
    val title: String,
    val price: String,
    val categoryType: CategoryType
)

enum class CategoryType{
    PARTY,
    CAMPING,
    CLASSIC,
    HOODIES,
    CASUAL
}