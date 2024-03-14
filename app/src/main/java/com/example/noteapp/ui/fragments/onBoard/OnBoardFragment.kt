package com.example.noteapp.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapter.OnBoardViewPagerAdapter


class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() {
        binding.viewPager.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
    }

    private fun setupListener() = with(binding.viewPager) {
        binding.onBtnSkip.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 1, true)
            }
        }

        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.onBtnBeggin.visibility=View.VISIBLE
                    binding.onBtnSkip.visibility = View.GONE
                } else {
                    binding.onBtnBeggin.visibility=View.GONE
                    binding.onBtnSkip.visibility = View.VISIBLE
                }
            }
        })
    }
}



