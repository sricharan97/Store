package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_shoe_list, container, false)

        // get the id of Linearlayout
        val linearLayout = binding.shoeListLinearLayout

        //Observe the data changes to shoe list
        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            val shoe: Shoe = it.last()
            val textView = TextView(context)
            textView.text = shoe.name
            textView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            Log.d("ShoeListFragment", "${textView.text} is the textview")
            linearLayout.addView(textView)


        })

        //floating action button functionality
        binding.floatingActionButton.setOnClickListener { v: View ->

            v.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())

        }

        return binding.root
    }


}