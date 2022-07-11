package ru.spoonbill.app.ui.phone_verif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.spoonbill.app.R
import ru.spoonbill.app.common.countdown.CountDownAnimation
import ru.spoonbill.app.databinding.FragmentPhoneVerificationBinding
import ru.spoonbill.app.utils.handleButtonState
import ru.spoonbill.app.utils.hideSoftInputKeyboard
import ru.spoonbill.app.utils.showSoftInputKeyboard

class PhoneVerificationFragment : Fragment(), CountDownAnimation.CountDownListener {

    private lateinit var mBinding: FragmentPhoneVerificationBinding
    private val args by navArgs<PhoneVerificationFragmentArgs>()
    private lateinit var mCountDownAnimation: CountDownAnimation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentPhoneVerificationBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val phone = args.phoneNumber
        mBinding.textLabel.append(phone)
        mBinding.textSendAgain.setOnClickListener { restartCountdown() }
        mBinding.tilVerificationCode.editText?.apply {
            showSoftInputKeyboard()
            handleButtonState(mBinding.buttonApply, 4)
        }
        mBinding.buttonApply.setOnClickListener {
            mBinding.tilVerificationCode.editText?.hideSoftInputKeyboard(requireActivity())
            findNavController().navigate(R.id.action_phoneVerificationFragment_to_mainNavHostFragment)
        }

        initCountDownAnimation()
        mCountDownAnimation.start()
    }

    private fun initCountDownAnimation() {
        mCountDownAnimation = CountDownAnimation(
            mTextView = mBinding.textSendAgainCountdown,
            mDuration = 1000L,
            startCount = 30
        )
        mCountDownAnimation.setCountDownListener(this@PhoneVerificationFragment)
    }

    private fun restartCountdown() {
        mBinding.textSendAgain.isEnabled = false
        mCountDownAnimation.start()
    }

    override fun onCountDownEnd(animation: CountDownAnimation?) {
        mBinding.textSendAgain.isEnabled = true
    }
}