package ru.spoonbill.droid.data.core.features.product.entity

data class Product(
    val id: Long,
    val productId: Long,
    val name: String,
    val description: String,
    val composition: String,
    val price: Float,
    val amount: Int,
    val unit: String = "pisces",
    val caloriesAmount: Int = 256,
    val proteinAmount: Float = 20.5f,
    val fatsAmount: Float = 19.1f,
    val carbsAmount: Float = 13.2f,
    val bestBeforeDate: String = "",
    val minStorageTemp: Int = -5,
    val maxStorageTemp: Int = 20,
    val manufacturer: String = "Manufacturer",
    val categoryId: Long = 1,
    val imageUrl: String,
    val quantityInCart: Int = 0,
    val isFavorite: Boolean = false,
)


val mockedProducts = listOf(
    Product(
        id = 0,
        productId = 1,
        name = "Super meat pizza 25cm",
        description = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        composition = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        price = 10f,
        amount = 1,
        unit = "pisces",
        imageUrl = "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395__480.jpg"
    ),
    Product(
        id = 0,
        productId = 2,
        name = "Super meat pizza 25cm",
        description = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        composition = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        price = 10f,
        amount = 1,
        unit = "pisces",
        imageUrl = "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395__480.jpg"
    ),
    Product(
        id = 0,
        productId = 3,
        name = "Super meat pizza 25cm",
        description = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        composition = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        price = 10f,
        amount = 1,
        unit = "pisces",
        imageUrl = "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395__480.jpg"
    ),
    Product(
        id = 0,
        productId = 4,
        name = "Super meat pizza 25cm",
        description = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        composition = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        price = 10f,
        amount = 1,
        unit = "pisces",
        imageUrl = "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395__480.jpg"
    ),
    Product(
        id = 0,
        productId = 2,
        name = "Super meat pizza 25cm",
        description = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        composition = "25cm, 6 slices, tomato sauce, Mozzarella,bacon, beef, Chorizo, Pepperoni, chicken",
        price = 10f,
        amount = 5,
        unit = "pisces",
        imageUrl = "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395__480.jpg"
    ),
).map(Product::toEntity)