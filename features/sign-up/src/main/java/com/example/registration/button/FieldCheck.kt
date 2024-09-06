package com.example.registration.button

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.core.R
import com.example.registration.databinding.ActivityRegistrationBinding
import com.example.registration.viewmodel.ViewModel
import javax.inject.Inject


class FieldCheck @Inject constructor(): FieldCheckContract {

    override fun fieldCheck(
        viewModel: ViewModel,
        binding: ActivityRegistrationBinding,
        context: Context
    ){
        with(binding) {
            viewModel.listenerFieldCheck.observe(context as LifecycleOwner) { states ->
                if (states) {
                    singUpButton.setBackgroundColor(ContextCompat.getColor(context, R.color.pink))
                    singUpButton.setOnClickListener {
                        TODO()
                    }
                } else {
                    singUpButton.setBackgroundColor(ContextCompat.getColor(context, R.color.pale_pink))
                }
            }
        }

    }


}

