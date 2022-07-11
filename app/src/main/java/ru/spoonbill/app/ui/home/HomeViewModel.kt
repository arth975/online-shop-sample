package ru.spoonbill.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.spoonbill.app.ui.home.model.*
import ru.spoonbill.app.utils.Resource
import ru.spoonbill.data.product.model.Product
import ru.spoonbill.data.product.model.Promotion
import ru.spoonbill.data.product.repository.ProductRepository
import ru.spoonbill.data.story.model.Story
import ru.spoonbill.data.story.repository.StoryRepository

class HomeViewModel(
    private val mProductRepository: ProductRepository,
    private val mStoryRepository: StoryRepository
) : ViewModel() {

    private val mResultFlow = MutableStateFlow<Resource<HomeResult>>(Resource.loading())
    val resultFlow = mResultFlow.asStateFlow()

    fun fetchResult() {
        viewModelScope.launch {
            val productCollections = async { fetchProductCollections() }
            val promotions = async { fetchPromotions() }
            val stories = async { fetchStories() }
            val result = HomeResult(
                productCollections.await(),
                promotions.await(),
                stories.await()
            )

            mResultFlow.value = Resource.success(result)
        }
    }

    private suspend fun fetchProductCollections(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): List<ProductCollection> = withContext(dispatcher) {
        val popular = async { mProductRepository.fetchPopularProducts().map(Product::toUi) }
        val recom = async { mProductRepository.fetchRecommendedProducts().map(Product::toUi) }
        val actual = async { mProductRepository.fetchActualProducts().map(Product::toUi) }

        listOf(
            ProductCollection("Популярное", popular.await()),
            ProductCollection("Рекомендуем", recom.await()),
            ProductCollection("Актуальные", actual.await()),
        )
    }

    private suspend fun fetchPromotions(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): List<PromotionUi> = withContext(dispatcher) {
        mProductRepository.fetchPromotions().map(Promotion::toUi)
    }

    private suspend fun fetchStories(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): List<StoryUi> = withContext(dispatcher) {
        mStoryRepository.fetchStories().map(Story::toUi)
    }
}