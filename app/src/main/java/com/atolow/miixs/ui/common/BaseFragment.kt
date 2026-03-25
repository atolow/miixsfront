package com.atolow.miixs.ui.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.atolow.miixs.util.ErrorHandler
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    
    protected fun observeLoading(isLoading: StateFlow<Boolean>, progressBar: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                isLoading.collect { loading ->
                    progressBar.visibility = if (loading) View.VISIBLE else View.GONE
                }
            }
        }
    }
    
    protected fun observeError(error: StateFlow<String?>) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                error.collect { errorMessage ->
                    errorMessage?.let {
                        ErrorHandler.handleError(requireContext(), Exception(it))
                    }
                }
            }
        }
    }
    
    protected fun observeErrorFlow(errorFlow: StateFlow<Throwable?>) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                errorFlow.collect { throwable ->
                    throwable?.let {
                        ErrorHandler.handleError(requireContext(), it)
                    }
                }
            }
        }
    }
}

