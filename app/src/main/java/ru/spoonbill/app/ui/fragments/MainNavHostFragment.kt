package ru.spoonbill.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.FragmentMainNavHostBinding

class MainNavHostFragment : Fragment() {
    private lateinit var mBinding: FragmentMainNavHostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainNavHostBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupBottomNavBar()
    }

    private fun setupBottomNavBar() {
        val navHost =
            childFragmentManager.findFragmentById(R.id.home_nav_host_container) as NavHostFragment
        mBinding.bottomNavBar.setupWithNavController(navHost.navController)
    }
}