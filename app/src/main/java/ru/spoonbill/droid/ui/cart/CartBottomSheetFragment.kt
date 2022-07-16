package ru.spoonbill.droid.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.spoonbill.app.databinding.BottomSheetCartBinding
import ru.spoonbill.droid.ui.cart.adapters.CartProductAdapter
import ru.spoonbill.droid.utils.addRepeatedJob
import ru.spoonbill.droid.ui.cart.CartViewModel.UiEvent.*
import ru.spoonbill.droid.ui.ExpandedBottomSheetDialogFragment
import ru.spoonbill.droid.ui.home.entity.ProductUi

class CartBottomSheetFragment : ExpandedBottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetCartBinding
    private val viewModel by viewModel<CartViewModel>()

    private lateinit var cartProductsAdapter: CartProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        collectFlows()
    }

    private fun setupViews() {
        binding.buttonPay.setOnClickListener { }
        cartProductsAdapter =
            CartProductAdapter(::onQuantityChanged) { viewModel.deleteProductEvent(it) }
        binding.rvCartProducts.adapter = cartProductsAdapter
        binding.rvCartProducts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onQuantityChanged(cartProduct: ProductUi) {
        viewModel.changeQuantityEvent(cartProduct)
    }

    private fun collectFlows() {
        viewLifecycleOwner.addRepeatedJob(Lifecycle.State.STARTED) {
            launch { collectCartProductsFlow() }
            launch { collectUiEventsFlow() }
        }
    }

    private suspend fun collectCartProductsFlow() =
        viewModel.cartProductsFlow().collectLatest { products ->
            binding.rvLinesGroup.isVisible = products.isNotEmpty()
            cartProductsAdapter.submitList(products)
        }

    private suspend fun collectUiEventsFlow() = viewModel.uiEventsFlow.collectLatest { event ->
        when (event) {
            is QuantityChangedEvent -> viewModel.updateProductQuantity(event.product)
            is DeleteProductEvent -> viewModel.deleteProductFromCart(event.product)
            is InitialEvent -> { /* Nothing to do here */ }
        }
    }

    companion object {
        fun show(fm: FragmentManager) {
            CartBottomSheetFragment().show(fm, TAG)
        }

        private val TAG = CartBottomSheetFragment::class.simpleName
    }
}