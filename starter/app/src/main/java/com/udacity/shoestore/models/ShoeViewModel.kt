package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel() : ViewModel() {

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList


    val shoes: MutableList<Shoe> = mutableListOf()

    fun addNewShoe(shoe: Shoe) {
        shoes.add(shoe)
        //Log.d("ShoeViewModel", "${shoes.last().name} has been added")
        _shoeList.value = shoes
        // Log.d("ShoeViewModel", "${shoeList.value} is the liveData")
    }

}