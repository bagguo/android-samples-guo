package com.example.android_lesson.fragment.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.android_lesson.databinding.FragmentBottomSheetTabVp2Binding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @title
 * @author Darren.eth
 */
class TabVP2BottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        private const val TAB_NUM = 4

        fun newInstance(): TabVP2BottomSheetDialogFragment {
            return TabVP2BottomSheetDialogFragment()
        }
    }

    private lateinit var binding: FragmentBottomSheetTabVp2Binding
    private val fragments = ArrayList<Fragment>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetTabVp2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initTab()
    }

    private fun FragmentBottomSheetTabVp2Binding.initTab() {
        for (i in 0 until TAB_NUM) {
            fragments.add(AFragment.newInstance())
            tab.addTab(binding.tab.newTab().setText("tab$i"))
        }

        val adapter = ChildFragmentsAdapter(requireActivity(), fragments)
        vp2.adapter = adapter

        TabLayoutMediator(tab, vp2) { tab, position ->
            tab.text = "tab$position"
        }.attach()

        val onPageChangeCallback: ViewPager2.OnPageChangeCallback =
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            }
        vp2.registerOnPageChangeCallback(onPageChangeCallback)
    }

    class ChildFragmentsAdapter(
        fragmentActivity: FragmentActivity,
        private val fragments: List<Fragment>
    ) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

}