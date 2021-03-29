package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoe_detail,
                container, false
        )

        binding.shoeViewModel = viewModel
        binding.setLifecycleOwner(this)


        //Cancel button functionality
        viewModel.onCancel.observe(viewLifecycleOwner, Observer { isCancelled ->
            if (isCancelled) {
                findNavController(this)
                        .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                viewModel.doneCancelling()
            }
        })


        //save button functionality
        viewModel.onSave.observe(viewLifecycleOwner, Observer { isSaved ->
            if (isSaved) {
                findNavController(this).navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                viewModel.doneSaving()
            }
        })

        return binding.root
    }

}