package ru.spoonbill.droid.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spoonbill.app.R
import ru.spoonbill.droid.ui.BaseFragment

class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delayedNavigateToMain()
    }

    private fun delayedNavigateToMain() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            navigateTo(R.id.action_splashFragment_to_mainNavHostFragment)
        }
    }
}