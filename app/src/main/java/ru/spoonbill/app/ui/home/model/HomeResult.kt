package ru.spoonbill.app.ui.home.model

data class HomeResult(
    val lists: List<ProductCollection>,
    val promotions: List<PromotionUi>,
    val stories: List<StoryUi>
)