package com.token.quicksell.ui.sell

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.token.quicksell.R
import com.token.quicksell.databinding.FragmentQuickSellBinding


class QuickSellFragment : Fragment() {

    private lateinit var binding: FragmentQuickSellBinding
    private val viewModel: SellViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(
            this,
            SellViewModel.Factory(activity.application)
        )[SellViewModel::class.java]
    }

    private val ZERO: String = "0"
    private val DOUBLE_ZERO: String = "00"
    private val TRIPLE_ZERO: String = "000"
    private val ONE: String = "1"
    private val TWO: String = "2"
    private val THREE: String = "3"
    private val FOUR: String = "4"
    private val FIVE: String = "5"
    private val SIX: String = "6"
    private val SEVEN: String = "7"
    private val EIGHT: String = "8"
    private val NINE: String = "9"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuickSellBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = ProductsAdapter(ProductListener { product ->
            viewModel.onProductClicked(product)
        })

        binding.recyclerviewProducts.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.selectedProduct.observe(viewLifecycleOwner) {
            Glide.with(this).load(it.image).into(binding.imageSelectedProduct)
            binding.textCategory.text = it.category
            binding.textviewProduct.text = it.name
        }

        initKeyboard()

        viewModel.currentAmount.observe(viewLifecycleOwner) {
            binding.textviewInputAmount.text = it
        }

        binding.buttonPay.setOnClickListener {
            if (binding.textviewInputAmount.text.toString().toInt() > 0 &&
                !binding.textCategory.text.isNullOrEmpty()
            ) {
                it.findNavController()
                    .navigate(QuickSellFragmentDirections.actionQuickSellFragmentToPaymentFragment(binding.textviewInputAmount.text.toString().toInt()))
            } else if (binding.textviewInputAmount.text.toString().toInt() == 0) {
                showDialog(getString(R.string.error_msg_amount_product))
            } else if (binding.textCategory.text.isNullOrEmpty()) {
                showDialog(getString(R.string.error_msg_choose_product))
            }
        }

        binding.keyboardLayout.buttonKeyboardOk.setOnClickListener {
            if (binding.textviewInputAmount.text.toString().toInt() > 0 &&
                !binding.textCategory.text.isNullOrEmpty()
            ) {
                it.findNavController()
                    .navigate(QuickSellFragmentDirections.actionQuickSellFragmentToPaymentFragment(binding.textviewInputAmount.text.toString().toInt()))
            } else if (binding.textviewInputAmount.text.toString().toInt() == 0) {
                showDialog(getString(R.string.error_msg_amount_product))
            } else if (binding.textCategory.text.isNullOrEmpty()) {
                showDialog(getString(R.string.error_msg_choose_product))
            }
        }

        return binding.root
    }

    private fun initKeyboard() {
        with(binding.keyboardLayout) {
            button1.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    ONE)
            }
            button2.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    TWO)
            }
            button3.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    THREE)
            }
            button4.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    FOUR)
            }
            button5.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    FIVE)
            }
            button6.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    SIX)
            }
            button7.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    SEVEN)
            }
            button8.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    EIGHT)
            }
            button9.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    NINE)
            }
            button0.setOnClickListener {
                viewModel.button(binding.textviewInputAmount.text.toString(),
                    ZERO)
            }
            button00.setOnClickListener {
                viewModel.doubleZeroButtonClick(binding.textviewInputAmount.text.toString(),
                    DOUBLE_ZERO)
            }
            button000.setOnClickListener {
                viewModel.tripleZeroButtonClick(binding.textviewInputAmount.text.toString(),
                    TRIPLE_ZERO)
            }
            buttonC.setOnClickListener { viewModel.clearScreen() }
            buttonDel.setOnClickListener { viewModel.backSpace(binding.textviewInputAmount.text.toString()) }
            buttonKeyboardOk
        }
    }

    private fun showDialog(msg: String) {
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.apply {
            setTitle("Attention")
            setMessage(msg)
            setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                }
            })
            create()
        }
        alertDialog.show()
    }
}
