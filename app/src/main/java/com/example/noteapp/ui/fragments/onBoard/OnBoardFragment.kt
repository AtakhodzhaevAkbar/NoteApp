package com.example.noteapp.ui.fragments.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapter.OnBoardViewPagerAdapter
import com.example.noteapp.utils.PreferenceHelper

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        preferenceHelper = PreferenceHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!preferenceHelper.isOnBoardShown) {
            initialize()
            setupListener()
        } else {
            navigateToMainScreen()
        }
    }

    private fun initialize() {
        binding.viewPager.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
    }

    private fun setupListener() {
        binding.onBtnSkip.setOnClickListener {
            if (binding.viewPager.currentItem < 3) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
            }
        }

        binding.onBtnBeggin.setOnClickListener {
            preferenceHelper.isOnBoardShown = true
            findNavController().navigate(OnBoardFragmentDirections.actionOnBoardFragmentToNoteFragment())
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.onBtnBeggin.visibility = View.VISIBLE
                    binding.onBtnSkip.visibility = View.GONE
                } else {
                    binding.onBtnBeggin.visibility = View.GONE
                    binding.onBtnSkip.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun navigateToMainScreen() {
        findNavController().navigate(OnBoardFragmentDirections.actionOnBoardFragmentToNoteFragment())
    }
}
