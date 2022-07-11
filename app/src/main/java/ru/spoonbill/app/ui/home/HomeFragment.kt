package ru.spoonbill.app.ui.home

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
import ru.spoonbill.app.databinding.FragmentHomeBinding
import ru.spoonbill.app.ui.home.model.ProductCollection
import ru.spoonbill.app.ui.home.model.StoryUi
import ru.spoonbill.app.ui.home.adapters.ParentAdapter
import ru.spoonbill.app.ui.home.adapters.StoryAdapter
import ru.spoonbill.app.ui.home.model.HomeResult
import ru.spoonbill.app.ui.home.model.PromotionUi
import ru.spoonbill.app.utils.Resource

class HomeFragment : Fragment() {

    private var mBinding: FragmentHomeBinding? = null
    private val mViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mBinding = FragmentHomeBinding.inflate(inflater, container, false)
            mViewModel.fetchResult()
        }
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectResourceFlow()
    }

    private fun collectResourceFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.resultFlow.collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            initRecyclerViews(resource.data)
                            hideShimmer()
                        }
                        is Resource.Failure ->
                            Toast.makeText(requireContext(), resource.e.message, Toast.LENGTH_SHORT)
                                .show()
                        is Resource.Loading ->
                            mBinding?.shimmerContainer?.startShimmer()
                    }
                }
            }
        }
    }

    private fun initRecyclerViews(result: HomeResult) {
        initParentRecyclerView(result.lists, result.promotions)
        initStoriesRecyclerView(result.stories)
    }

    private fun initStoriesRecyclerView(stories: List<StoryUi>) = mBinding?.rvStories?.let {
        it.layoutManager =
            LinearLayoutManager(requireContext()).apply { orientation = RecyclerView.HORIZONTAL }
        it.adapter = StoryAdapter(stories)
        it.visibility = View.VISIBLE
    }

    private fun initParentRecyclerView(
        lists: List<ProductCollection>,
        sliderItem: List<PromotionUi>
    ) = mBinding?.rvProductsParent?.let {
        it.layoutManager = LinearLayoutManager(requireContext())
        it.adapter = ParentAdapter(lists, sliderItem)
        it.isNestedScrollingEnabled = false
        it.visibility = View.VISIBLE
        it.setHasFixedSize(false)
    }

    private fun hideShimmer() = mBinding?.shimmerContainer?.let {
        it.stopShimmer()
        it.visibility = View.GONE
    }
}