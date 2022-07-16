package ru.spoonbill.droid.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentFavoritesBinding
import ru.spoonbill.droid.utils.addRepeatedJob
import ru.spoonbill.droid.utils.viewBinding
import ru.spoonbill.droid.ui.favorites.FavoritesViewModel.UiState.*
import ru.spoonbill.droid.ui.favorites.adapter.SimpleProductAdapter
import ru.spoonbill.droid.ui.BaseFragment
import ru.spoonbill.droid.ui.home.entity.ProductUi
import ru.spoonbill.droid.ui.single_product.SingleProductBottomSheetFragment
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductSharedViewModel

class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel by viewModel<FavoritesViewModel>()
    private val sharedViewModel by sharedViewModel<SingleProductSharedViewModel>()
    private val favoritesAdapter by lazy {
        SimpleProductAdapter(
            ::showSingleProductBottomSheetFragment,
            viewModel::changeFavoriteStatus,
            viewModel::changeQuantityInCart
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        viewModel.initialize()
        collectFlows()
    }

    private fun setupViews() {
        binding.rvFavoriteProducts.adapter = favoritesAdapter
        binding.rvFavoriteProducts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showSingleProductBottomSheetFragment(product: ProductUi) {
        sharedViewModel.selectedProduct = product
        SingleProductBottomSheetFragment.show(parentFragmentManager)
    }

    private fun collectFlows() {
        viewLifecycleOwner.addRepeatedJob(Lifecycle.State.STARTED) {
            launch { collectUiStateFlow() }
        }
    }

    private suspend fun collectUiStateFlow() = viewModel.uiStateFlow.collectLatest { state ->
        when (state) {
            is Success -> favoritesAdapter.submitList(state.data)
            is Failure -> showErrorMessage(state.e.message)
            is Loading -> {}
        }
    }
}