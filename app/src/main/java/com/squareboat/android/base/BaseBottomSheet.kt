package com.squareboat.android.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareboat.android.R
import com.squareboat.android.data.model.ErrorResponse

abstract class BaseBottomSheet : BottomSheetDialogFragment(), BaseErrorInterface {
    private var unbinder: Unbinder? = null
    private var activity: AppCompatActivity? = null

    @LayoutRes
    protected abstract fun layoutRes(): Int
    protected open fun init() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutRes(), container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
        unbinder = null
    }

    override fun showInternetError() {
        Toast.makeText(requireContext(), R.string.no_internet, Toast.LENGTH_LONG).show()
    }

    override fun showSocketTimeoutError() {
        Toast.makeText(requireContext(), R.string.no_internet, Toast.LENGTH_LONG).show()
    }

    override fun showOtherError(errorResponse: ErrorResponse) {
        Toast.makeText(
            requireContext(),
            errorResponse.message ?: getString(R.string.something_went_wrong),
            Toast.LENGTH_LONG
        ).show()
    }
}