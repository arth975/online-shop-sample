package ru.spoonbill.droid.ui.single_product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.spoonbill.app.databinding.BottomSheetSingleProductBinding
import ru.spoonbill.droid.utils.addRepeatedJob
import ru.spoonbill.droid.common.extensions.loadCenterCropImage
import ru.spoonbill.droid.common.extensions.nonNullCollect
import ru.spoonbill.droid.ui.ExpandedBottomSheetDialogFragment
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductSharedViewModel
import ru.spoonbill.droid.ui.single_product.viewmodel.SingleProductViewModel

class SingleProductBottomSheetFragment : ExpandedBottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetSingleProductBinding
    private val viewModel by viewModel<SingleProductViewModel>()
    private val sharedViewModel by sharedViewModel<SingleProductSharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetSingleProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        collectUiStateFlow()
        viewModel.initialize(sharedViewModel.selectedProduct)
    }

    private fun setupViews() {
        binding.product = sharedViewModel.selectedProduct
        binding.imageProduct.loadCenterCropImage(sharedViewModel.selectedProduct?.imageUrl)
        binding.priceCounter.setOnCounterChangedListener { quantity -> viewModel.changeQuantity(quantity) }
        binding.checkboxIsFavorite.setOnCheckedChangeListener { _, isChecked -> viewModel.changeFavoriteStatus(isChecked) }
        binding.buttonAddToCart.setOnClickListener {
            viewModel.addProductIntoCart()
            dismiss()
        }
    }

    private fun collectUiStateFlow() {
        viewLifecycleOwner.addRepeatedJob(Lifecycle.State.STARTED) {
            viewModel.uiStateFlow.nonNullCollect { state ->
                binding.checkboxIsFavorite.isChecked = state.product?.isFavorite ?: false
                binding.buttonAddToCart.setPrice(state.totalPrice.toInt())
                binding.priceCounter.setInitialCount(state.product?.quantityInCart ?: 0)
                if(state.errorMessages.isNotEmpty()) {
                    showErrorAndGoBack(state.errorMessages[0])
                }
            }
        }
    }

    private fun showErrorAndGoBack(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        dismiss()
    }

    companion object {
        fun show(fm: FragmentManager) {
            SingleProductBottomSheetFragment().show(fm, TAG)
        }

        private val TAG = SingleProductBottomSheetFragment::class.simpleName
    }
}