package com.example.guo.ui.anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.guo.R

class AFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_anim_a, container, false)
        view.findViewById<Button>(R.id.btnNext).setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .setCustomAnimations(
                    R.anim.fragment_slide_in_right,
                    R.anim.fragment_slide_out_left,
                    R.anim.fragment_slide_in_left,
                    R.anim.fragment_slide_out_right,
                ).replace(R.id.fragment_container, BFragment())
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
