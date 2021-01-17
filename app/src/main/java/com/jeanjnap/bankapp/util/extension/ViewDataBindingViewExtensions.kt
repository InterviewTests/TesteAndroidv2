package com.jeanjnap.bankapp.util.extension

import androidx.databinding.ViewDataBinding

inline fun <reified CLASS_DATA_BINDING_GENERATED> ViewDataBinding.adapterDataBindingCast() =
    this as? CLASS_DATA_BINDING_GENERATED
        ?: throw ClassCastException("${CLASS_DATA_BINDING_GENERATED::class.java}")
