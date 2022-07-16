package ru.spoonbill.droid.ui

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.spoonbill.droid.ui.cart.CartBottomSheetFragment

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    protected fun showErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun navigateTo(@IdRes actionId: Int) {
        findNavController().navigate(actionId)
    }

    protected fun showCartBottomSheetFragment() {
        CartBottomSheetFragment.show(parentFragmentManager)
    }
}