package ru.spoonbill.app.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentCatalogBinding
import ru.spoonbill.app.ui.catalog.adapters.ParentCategoriesAdapter
import ru.spoonbill.app.ui.catalog.model.CatalogResult
import ru.spoonbill.app.ui.catalog.model.ParentCategoryUi
import ru.spoonbill.app.ui.home.adapters.ProductCardAdapter
import ru.spoonbill.app.ui.home.model.ProductUi
import ru.spoonbill.app.utils.Resource

class CatalogFragment : Fragment() {

    private var mBinding: FragmentCatalogBinding? = null
    private val mViewModel by viewModel<CatalogViewModel>()

    private val mGridSpacing by lazy { resources.getDimension(R.dimen.category_spacing).toInt() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mBinding = FragmentCatalogBinding.inflate(inflater, container, false)
            mViewModel.fetchResult()
        }
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.resultFlow.collectLatest { resource ->
                    when (resource) {
                        is Resource.Success ->
                            initRecyclerViews(resource.data)
                        is Resource.Failure ->
                            Toast.makeText(requireContext(), resource.e.message, Toast.LENGTH_SHORT)
                                .show()
                        is Resource.Loading -> {}
                    }
                }
            }
        }
    }

    private fun initRecyclerViews(result: CatalogResult) {
        initParentRecyclerView(result.parentCategories)
        initRecommendedProductsRecyclerView(result.recommendedProducts)
    }

    private fun initParentRecyclerView(
        parentCategories: List<ParentCategoryUi>
    ) = mBinding?.rvParentCategories?.let {
        it.adapter = ParentCategoriesAdapter(parentCategories, mGridSpacing)
        it.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initRecommendedProductsRecyclerView(
        recommendedProducts: List<ProductUi>
    ) = mBinding?.layoutProductParent?.rvProducts?.let {
        it.adapter = ProductCardAdapter(recommendedProducts)
        it.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

}