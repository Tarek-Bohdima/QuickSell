package com.token.quicksell.ui.payment

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.token.quicksell.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {

    lateinit var binding: FragmentSuccessBinding
    lateinit var check: ImageView

    private val backPressedDispatcher = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Redirect to our own function
            onBackPressed()
        }
    }

    // credits to https://stackoverflow.com/a/54029413/8899344
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).apply {
            // Redirect system "Back" press to our dispatcher
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedDispatcher)

            // Setup action bar to work with NavController
            setupActionBarWithNavController(findNavController())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            // Redirect "Up/Home" button clicks to our own function
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun onBackPressed() {
        findNavController().navigate(SuccessFragmentDirections.actionSuccessFragmentToHomeFragment())
    }

    override fun onDestroyView() {
        // It is optional to remove since our dispatcher is lifecycle-aware. But it wouldn't hurt to just remove it to be on the safe side.
        backPressedDispatcher.remove()
        super.onDestroyView()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentSuccessBinding.inflate(inflater, container, false)

        binding.buttonOk.setOnClickListener {
            it.findNavController()
                .navigate(SuccessFragmentDirections.actionSuccessFragmentToHomeFragment())
        }

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