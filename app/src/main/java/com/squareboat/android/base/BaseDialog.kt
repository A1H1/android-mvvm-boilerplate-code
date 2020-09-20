package com.squareboat.android.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.DaggerDialogFragment

abstract class BaseDialog : DaggerDialogFragment() {
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
        dialog.window?.setGravity(Gravity.CENTER)
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

    override fun onResume() {
        super.onResume()

        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        dialog?.window?.attributes?.width = LinearLayout.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes?.height = LinearLayout.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
    }
}