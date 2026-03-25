package com.atolow.miixs.ui.common

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.atolow.miixs.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) : Dialog(context) {
    private val binding: DialogLoadingBinding
    
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogLoadingBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
    
    fun setMessage(message: String) {
        binding.tvMessage.text = message
    }
}

