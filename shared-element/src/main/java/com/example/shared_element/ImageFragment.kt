package com.example.shared_element

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.shared_element.databinding.FragmentImageBinding

class ImageFragment : Fragment(R.layout.fragment_image) {

    var _binding: FragmentImageBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageBinding.inflate(layoutInflater, container, false)

        // 获取参数
        arguments?.getInt("source_id")?.let { id ->
//            binding.image.transitionName = id.toString()
        }
        arguments?.getString("transition_name")?.let { name ->
            binding.image.transitionName = name
        }
        arguments?.getInt("source_id")?.let { id ->
//            binding.image.transitionName = id.toString()
            binding.image.setImageResource(id)
        }
        arguments?.getString("title")?.let { title ->
            binding.titleText.text = title
        }
        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        return binding.root
//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}