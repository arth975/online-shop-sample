package ru.spoonbill.data.category.data_source

import kotlinx.coroutines.delay
import ru.spoonbill.data.category.model.Category
import ru.spoonbill.data.category.model.ParentCategory

private val childCategories = listOf(
    Category(
        1,
        "Молоко, сливки",
        "https://s5.stc.all.kpcdn.net/family/wp-content/uploads/2022/03/moloko-polza-i-vred.jpg"
    ),
    Category(
        2,
        "Яйцо",
        "https://img.vkusvill.ru/pim/images/site_LargeWebP/990fda4e-40a1-4bf2-a459-3e86411dbf7d.png?1652776284.6079"
    ),
    Category(
        3,
        "Творог, сырки, творожная масса",
        "https://cdnn1.img.armeniasputnik.am/img/07e4/09/1a/24609282_0:0:1920:1080_1920x0_80_0_0_a8224117882a93edf73ffdd994d4970e.jpg"
    ),
    Category(
        4,
        "Майонез, заправки для салата",
        "https://www.moulinex.ru/medias/?context=bWFzdGVyfHJvb3R8ODg5OTN8aW1hZ2UvanBlZ3xoZmIvaGI2LzE0NzczMDM1MzY4NDc4LmpwZ3xkYmQwMjk3MGQ3M2IxOTZlZjQ2YTUwMTNiNzYzYjA3ZDQyMjY5NDY5OGQxNWE0MDhjY2QxMmMwZTc4YzlkNzlj"
    ),
)

private val parentCategories = listOf(
    ParentCategory(1, "Молоко, яйцо, майонез", childCategories),
    ParentCategory(2, "Молоко, яйцо, майонез", childCategories),
    ParentCategory(3, "Молоко, яйцо, майонез", childCategories),
    ParentCategory(4, "Молоко, яйцо, майонез", childCategories),
)

class CategoryRemoteDataSource : CategoryDataSource {
    override suspend fun fetchCategories(): List<ParentCategory> {
        delay(3000)
        return parentCategories
    }
}