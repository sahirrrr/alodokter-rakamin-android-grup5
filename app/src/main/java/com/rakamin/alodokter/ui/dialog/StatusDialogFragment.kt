package com.rakamin.alodokter.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.utils.REGISTER_USER_STATUS
import com.rakamin.alodokter.databinding.FragmentStatusDialogBinding

class StatusDialogFragment : DialogFragment() {
    private var _binding: FragmentStatusDialogBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStatusDialogBinding.inflate(inflater, container, false)

        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        root = binding?.root
        return  root
    }

    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels

        dialog?.window?.setLayout(width-64, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val registerStatus = requireArguments().getBoolean(REGISTER_USER_STATUS)
            if (registerStatus) setStatus(R.string.label_success, R.string.description_success, R.drawable.ic_success, R.color.success)
            else setStatus(R.string.label_failed, R.string.description_failed, R.drawable.ic_failed, R.color.error)
        }

        binding?.btnStatus?.setOnClickListener {
            dismiss()
        }

        binding?.ivClose?.setOnClickListener {
            dismiss()
        }
    }

    private fun setStatus(title: Int, description: Int, icon: Int ,color: Int) {
        binding?.tvStatus?.text = getString(title)
        binding?.tvDescription?.text = getString(description)
        binding?.ivLogoStatus?.setImageResource(icon)
        binding?.btnStatus?.setBackgroundColor(color)
    }
}