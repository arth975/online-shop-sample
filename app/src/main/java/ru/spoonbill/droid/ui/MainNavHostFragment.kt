package ru.spoonbill.droid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentMainNavHostBinding
import ru.spoonbill.droid.ui.cart.CartBottomSheetFragment

class MainNavHostFragment : Fragment() {
    private lateinit var binding: FragmentMainNavHostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainNavHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupBottomNavBar()
        setupViews()
    }

    private fun setupViews() {
        binding.fab.setOnClickListener { CartBottomSheetFragment.show(parentFragmentManager) }
    }

    private fun setupBottomNavBar() {
        val navHost =
            childFragmentManager.findFragmentById(R.id.home_nav_host_container) as NavHostFragment
        binding.bottomNavBar.setupWithNavController(navHost.navController)
    }
}