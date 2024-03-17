package com.example.noteapp.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardPagingBinding
import com.example.noteapp.utils.PreferenceHelper


class OnBoardPagingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        if(requireArguments().getInt(ARG_ONBOARD_POSITION)==2){
            PreferenceHelper(requireContext()).isOnBoardShown=true
        }
    }
    private fun initialize()=with(binding){
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0->{
                onTxt.text="Очень удобный функционал"
                firstDot.setImageResource(R.drawable.dot_orange)
                animationView.setAnimation("animation.json")
                animationView.playAnimation()
            }
            1->{
                onTxt.text="Быстрый качественный продукт"
                secondDot.setImageResource(R.drawable.dot_orange)
                animationView.setAnimation("animation_second.json")
                animationView.playAnimation()
            }
            2->{
                onTxt.text="Куча функций и интересных фишек"
                thirdDot.setImageResource(R.drawable.dot_orange)
                animationView.setAnimation("animation_third.json")
                animationView.playAnimation()
            }
        }
    }
    companion object{
        const val ARG_ONBOARD_POSITION="onBoard"
    }
}