package com.example.shared_element

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.shared_element.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    var _binding: FragmentMainBinding? = null


    val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater)

        binding.image.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.image to "transitionName")
            val bundle = Bundle().apply {
                putInt("source_id", R.drawable.shiroko)
                putString("transition_name", "transitionName")
                putString("title", "shiroko")
            }

            findNavController().navigate(
                R.id.action_mainFragment_to_imageFragment,
                bundle,
                null,
                extras
            )
        }

        binding.image2.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.image2 to "transitionName2")
            // 传参
            val bundle = Bundle().apply {
                putInt("source_id", R.drawable.shiroko2)
                putString("transition_name", "transitionName2")
                putString("title", "shiroko 2")
            }

            findNavController().navigate(
                R.id.action_mainFragment_to_imageFragment,
                bundle,
                null,
                extras
            )
        }

        return _binding!!.root
//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}