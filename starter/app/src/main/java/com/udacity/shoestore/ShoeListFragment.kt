package com.udacity.shoestore

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private lateinit var linearLayout: LinearLayout

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list, container, false
        )

        // get the id of Linearlayout
        linearLayout = binding.listLinearLayout

        //Observe the data changes to shoe list
        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            for (shoe in it) {
                //val custInflater = inflater.inflate(R.layout.shoe_list_item, null, false)
                inflateItemLayout(shoe, inflater)
            }
        })

        //floating action button functionality
        binding.floatingActionButton.setOnClickListener { v: View ->

            v.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())

        }

        setHasOptionsMenu(true)
        return binding.root
    }


    private fun inflateItemLayout(shoe: Shoe, inflater: LayoutInflater) {
        val itemBinding: ShoeListItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_item, null, false
        )
        itemBinding.shoe = shoe
        linearLayout.addView(itemBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loginFragment -> {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}