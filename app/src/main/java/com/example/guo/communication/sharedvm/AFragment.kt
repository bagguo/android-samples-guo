package com.example.guo.communication.sharedvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.guo.R

class AFragment : Fragment() {

    companion object{
        private const val TAG = "AFragment"
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_a2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.btn_to_b).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BFragment())
                .addToBackStack(null)
                .commit()
        }

        sharedViewModel.cResultLiveData.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireContext(), "AFragment 收到结果：$result", Toast.LENGTH_SHORT).show()
            Log.i(TAG, "onViewCreated: ====AFragment 收到结果：$result")
        }
    }
}