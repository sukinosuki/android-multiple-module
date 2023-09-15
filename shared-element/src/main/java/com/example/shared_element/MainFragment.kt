package com.example.shared_element

import android.os.Bundle
import android.util.Log
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

    override fun onPause() {
        super.onPause()
        Log.i("hanami", "onPause: main fragment pause")
        // tip: 修改status bar 颜色
//        requireActivity().window.statusBarColor = resources.getColor(R.color.blue, requireActivity().theme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater)

//        requireActivity().window.statusBarColor = resources.getColor(R.color.blue, requireActivity().theme)
        // 需要提交设置transitionName, 在点击事件里设置会不生效
        // 条件1
        val transitionName = "shiroko"
        binding.image.transitionName = transitionName

        binding.image.setOnClickListener {
            // 条件2: binding.image to transitionName的transitionName需要和image.transitionName一致
            val extras = FragmentNavigatorExtras(binding.image to transitionName)
            // 传参方式一:
            val bundle = Bundle().apply {
                putInt("source_id", R.drawable.shiroko)
                putString("transition_name", transitionName)
                putString("title", "shiroko")
            }
            // 方式一
            findNavController().navigate(
                R.id.action_mainFragment_to_imageFragment,
                bundle,
                null,
                extras // TODO: 如果传了extras参数, 过渡动画不会生效
            )

            // TODO:
            // https://medium.com/@serbelga/shared-elements-in-android-navigation-architecture-component-bc5e7922ecdf
            // 传参方式二:
//            val action = MainFragmentDirections.actionMainFragmentToImageFragment(
//                sourceId = R.drawable.shiroko,
//                title = "shiroko", transitionName = transitionName
//            )
            // 方式二
//            findNavController().navigate(action, extras)
        }

        val transitionName2 = "shiroko2"
        binding.image2.transitionName = transitionName2
        binding.image2.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.image2 to transitionName2)

            // 传参
            val bundle = Bundle().apply {
                putInt("source_id", R.drawable.shiroko2)
                putString("transition_name", transitionName2)
                putString("title", "shiroko 2")
            }
            findNavController().navigate(
                R.id.action_mainFragment_to_imageFragment,
                bundle,
                null,
                null
            )

//            val action = MainFragmentDirections.actionMainFragmentToImageFragment(
//                sourceId = R.drawable.shiroko2,
//                title = "shiroko 2",
//                transitionName = transitionName2
//            )
//
//            findNavController().navigate(action, extras)
        }

        return _binding!!.root
//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}