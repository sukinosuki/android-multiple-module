package com.example.shared_element

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shared_element.databinding.FragmentImageBinding

class ImageFragment : Fragment(R.layout.fragment_image) {

    private var _binding: FragmentImageBinding? = null

    private val binding get() = _binding!!

    // 接收方式二传参:
//    val args: ImageFragmentArgs by navArgs<ImageFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowInsetsControllerCompat(requireActivity().window, requireActivity().window.decorView).apply {
//            // hide status bar
//            hide(WindowInsetsCompat.Type.statusBars())
//            // Allow showing the status bar with swiping from top to bottom
////            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//        }

//        requireActivity().window.statusBarColor = resources.getColor(R.color.orange, requireActivity().theme)
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
        // 接收方式一传参: arguments(Bundle)获取
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

        // tip: 动态修改toolbar navigation icon
        binding.toolbar.navigationIcon?.setTint(requireActivity().resources.getColor(R.color.white, requireActivity().theme))

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }

        Log.i("hanami", "onCreateView: arguments: $arguments")
//        Log.i("hanami", "onCreateView: args: $args")
//
//        // 接收方式二传参:
//        binding.titleText.text = args.title
//        binding.image.setImageResource(args.sourceId)
//        binding.image.transitionName = args.transitionName

        Log.i("hanami", "onCreateView: ${binding.image.transitionName}")
        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        return binding.root
//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}