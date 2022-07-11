package ru.spoonbill.app.ui.home.model

import ru.spoonbill.data.product.model.Product
import ru.spoonbill.data.product.model.Promotion
import ru.spoonbill.data.story.model.Story

fun Product.toUi() = ProductUi(
    id = this.id,
    name = this.name,
    price = this.price,
    isFavorite = this.isFavorite
)

fun Promotion.toUi() = PromotionUi(
    uri = this.uri
)

fun Story.toUi() = StoryUi(
    id = this.id
)