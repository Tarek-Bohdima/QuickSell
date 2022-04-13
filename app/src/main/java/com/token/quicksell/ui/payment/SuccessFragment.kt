package com.token.quicksell.ui.payment

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.token.quicksell.R
import com.token.quicksell.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {

    lateinit var binding: FragmentSuccessBinding
    lateinit var check: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentSuccessBinding.inflate(inflater, container, false)

        binding.buttonOk.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_successFragment_to_homeFragment)
        )

        check = binding.imageSuccess

        rotateImage()

        scaleImage()
        return binding.root
    }

    private fun scaleImage() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(check, scaleX, scaleY)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    private fun rotateImage() {
        val animator: ObjectAnimator = ObjectAnimator.ofFloat(check, View.ROTATION, -360f, 0f)
        animator.duration = 300
        animator.start()
    }
}