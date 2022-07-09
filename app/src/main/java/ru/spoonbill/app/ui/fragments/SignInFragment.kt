package ru.spoonbill.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.spoonbill.app.databinding.FragmentSignInBinding
import ru.spoonbill.app.utils.handleButtonState
import ru.spoonbill.app.utils.showSoftInputKeyboard

class SignInFragment : Fragment() {

    private lateinit var mBinding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSignInBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.tilPhoneNumber.editText?.apply {
            showSoftInputKeyboard()
            handleButtonState(mBinding.buttonSendCode, 10)
        }
        mBinding.buttonSendCode.setOnClickListener { navigateToPhoneVerificationFragment() }
    }

    private fun navigateToPhoneVerificationFragment() {
        val phoneNumber =
            mBinding.tilPhoneNumber.prefixText?.toString() + mBinding.tilPhoneNumber.editText?.text?.toString()
        val action =
            SignInFragmentDirections.actionSignInFragmentToPhoneVerificationFragment(phoneNumber)
        findNavController().navigate(action)
    }
}