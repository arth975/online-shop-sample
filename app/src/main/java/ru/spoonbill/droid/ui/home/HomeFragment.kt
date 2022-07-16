package ru.spoonbill.droid.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentHomeBinding
import ru.spoonbill.droid.utils.addRepeatedJob
import ru.spoonbill.droid.utils.viewBinding
import ru.spoonbill.droid.common.extensions.nonNullCollect
import ru.spoonbill.droid.ui.BaseFragment
import ru.spoonbill.droid.ui.home.adapters.ProductCardAdapter
import ru.spoonbill.droid.ui.home.adapters.PromotionAdapter
import ru.spoonbill.droid.ui.home.adapters.StoryAdapter
import ru.spoonbill.droid.ui.home.entity.*
import ru.spoonbill.droid.ui.single_product.SingleProductBottomSheetFragment
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductSharedViewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModel<HomeViewModel>()
    private val sharedViewModel by sharedViewModel<SingleProductSharedViewModel>()

    private val recommendedProductsAdapter by lazy { ProductCardAdapter(::showSingleBottomSheetFragment, viewModel::changeFavoriteStatus, ::showCartBottomSheetFragment) }
    private val actualProductsAdapter by lazy { ProductCardAdapter(::showSingleBottomSheetFragment, viewModel::changeFavoriteStatus, ::showCartBottomSheetFragment) }
    private val popularProductsAdapter by lazy { ProductCardAdapter(::showSingleBottomSheetFragment, viewModel::changeFavoriteStatus, ::showCartBottomSheetFragment) }
    private val storiesAdapter by lazy { StoryAdapter() }
    private val promotionsAdapter by lazy { PromotionAdapter() }

    private val horizontalLayoutManager: LinearLayoutManager
        get() = LinearLayoutManager(binding.root.context, HORIZONTAL, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        viewModel.initialize()
        collectResourceFlow()
    }

    private fun collectResourceFlow() {
        viewLifecycleOwner.addRepeatedJob(Lifecycle.State.STARTED) {
            viewModel.uiStateFlow.nonNullCollect { state ->
                if(state.errorMessages != null) {
                    showErrorMessage(state.errorMessages[0])
                } else {
                    if(state.isLoading) {
                        showShimmer()
                    } else {
                        hideShimmer()
                    }
                    recommendedProductsAdapter.submitList(state.recommendedProducts)
                    actualProductsAdapter.submitList(state.actualProducts)
                    popularProductsAdapter.submitList(state.popularProducts)
                    storiesAdapter.submitList(state.stories)
                    promotionsAdapter.submitList(state.promotions)
                }
            }
        }
    }

    private fun setupViews() {
        setupProductRecyclerView(binding.rvRecommendedProducts, recommendedProductsAdapter)
        setupProductRecyclerView(binding.rvPopularProducts, popularProductsAdapter)
        setupProductRecyclerView(binding.rvActualProducts, actualProductsAdapter)
        setupStoriesRecyclerView()
        setupPromotionsRecyclerView()
    }

    private fun setupStoriesRecyclerView() = binding.rvStories.also {
        it.setHasFixedSize(true)
        it.adapter = storiesAdapter
        it.layoutManager = horizontalLayoutManager
    }

    private fun setupProductRecyclerView(recyclerView: RecyclerView, adapter: ProductCardAdapter) = recyclerView.also {
        it.setHasFixedSize(true)
        it.adapter = adapter
        it.layoutManager = horizontalLayoutManager
    }

    private fun setupPromotionsRecyclerView() = binding.rvPromotions.also {
        it.setHasFixedSize(true)
        it.adapter = promotionsAdapter
        it.layoutManager = LinearLayoutManager(binding.root.context, HORIZONTAL, false)
    }

    private fun showSingleBottomSheetFragment(product: ProductUi) {
        sharedViewModel.selectedProduct = product
        SingleProductBottomSheetFragment.show(parentFragmentManager)
    }

    private fun hideShimmer() = binding.shimmerContainer.also {
        it.stopShimmer()
        it.visibility = View.GONE
    }

    private fun showShimmer() = binding.shimmerContainer.also {
        it.startShimmer()
        it.visibility = View.VISIBLE
    }
}