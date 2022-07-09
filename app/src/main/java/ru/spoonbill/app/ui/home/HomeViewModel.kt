package ru.spoonbill.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.spoonbill.app.model.ProductListItem
import ru.spoonbill.app.model.ProductUI
import ru.spoonbill.app.model.StoryUI
import ru.spoonbill.app.ui.home.image_slider.ImageSliderItem
import ru.spoonbill.app.utils.Resource


private val stories = listOf(
    StoryUI(1), StoryUI(2), StoryUI(3), StoryUI(4), StoryUI(5), StoryUI(6), StoryUI(7),
)

private val products = listOf(
    ProductUI(1, "Продукт 1", 100f, false),
    ProductUI(2, "Продукт 1", 100f, false),
    ProductUI(3, "Продукт 1", 100f, false),
    ProductUI(4, "Продукт 1", 100f, false),
    ProductUI(5, "Продукт 1", 100f, false),
)

private val lists = listOf(
    ProductListItem("Популярное", products),
    ProductListItem("Рекомендуем", products),
    ProductListItem("Актуальные", products),
)

private val sliderItems = listOf(
    ImageSliderItem("https://www.guidingtech.com/wp-content/uploads/HD-Mouth-Watering-Food-Wallpapers-for-Desktop-12_4d470f76dc99e18ad75087b1b8410ea9.jpg"),
    ImageSliderItem("https://i.pinimg.com/originals/f0/b6/15/f0b615f78dd809d68ec389f4bc8d94bb.jpg"),
    ImageSliderItem("https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80"),
)

class HomeViewModel : ViewModel() {

    private val mResultFlow = MutableStateFlow<Resource<HomeResult>>(Resource.loading())
    val resultFlow = mResultFlow.asStateFlow()

    fun fetchResult() {
        viewModelScope.launch {
            delay(3000)
            mResultFlow.value = Resource.success(HomeResult(lists, sliderItems, stories))
        }
    }

    data class HomeResult(
        val lists: List<ProductListItem>,
        val sliderItems: List<ImageSliderItem>,
        val stories: List<StoryUI>
    )
}