package ru.spoonbill.droid.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.spoonbill.app.R

abstract class ExpandedBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_Spoonbill_RoundedBottomSheetDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { setupFullScreen(it as BottomSheetDialog) }
        return dialog
    }

    private fun setupFullScreen(bottomSheet: BottomSheetDialog) {
        bottomSheet.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            ?.let { parent ->
                setupFullScreenLayoutParams(parent)
                with(BottomSheetBehavior.from(parent)) {
                    skipCollapsed = true
                    state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
    }

    private fun setupFullScreenLayoutParams(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }
}