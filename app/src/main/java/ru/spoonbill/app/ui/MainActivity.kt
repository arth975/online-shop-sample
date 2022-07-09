package ru.spoonbill.app.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.fragment.NavHostFragment
import ru.spoonbill.app.R
import ru.spoonbill.app.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mSystemBarsType by lazy { WindowInsets.Type.systemBars() }
    private val mSystemUiHideNavigationFlag =
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
    private lateinit var mInsetsController: WindowInsetsControllerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        hideSystemBars()
        val navHostFragment = mBinding.graphContainer.getFragment<NavHostFragment>()

        navHostFragment.navController.addOnDestinationChangedListener { _, dest, _ ->
            if (dest.id != R.id.splashFragment) {
                window.setSystemBarsDarkIcons()
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P)
                    mInsetsController.show(mSystemBarsType)
                else
                    window.clearFlags(mSystemUiHideNavigationFlag)
            }
        }
    }

    private fun hideSystemBars() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P)
            mInsetsController.hide(mSystemBarsType)
        else
            window.decorView.systemUiVisibility = mSystemUiHideNavigationFlag
    }

    private fun Window.setSystemBarsDarkIcons(dark: Boolean = true) {
        setStatusBarDarkIcons(dark)
        setNavigationBarDarkIcons(dark)
    }

    private fun Window.setStatusBarDarkIcons(dark: Boolean = true) {
        when {
            Build.VERSION_CODES.R <= Build.VERSION.SDK_INT ->
                insetsController?.setSystemBarsAppearance(
                    if (dark) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            Build.VERSION_CODES.M <= Build.VERSION.SDK_INT -> decorView.systemUiVisibility =
                if (dark)
                    decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                else
                    decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            else -> if (dark)
                statusBarColor = Color.BLACK

        }
    }

    private fun Window.setNavigationBarDarkIcons(dark: Boolean = true) {
        when {
            Build.VERSION_CODES.R <= Build.VERSION.SDK_INT ->
                insetsController?.setSystemBarsAppearance(
                    if (dark) WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS else 0,
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )
            Build.VERSION_CODES.O <= Build.VERSION.SDK_INT -> decorView.systemUiVisibility =
                if (dark)
                    decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                else
                    decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()

            else -> if (dark)
                navigationBarColor = Color.BLACK

        }
    }
}