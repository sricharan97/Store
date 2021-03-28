package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel() : ViewModel() {

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    private val _addedShoe = MutableLiveData<Boolean>()
    val addedShoe: LiveData<Boolean>
        get() = _addedShoe


    /** Expose MutableLiveData for edit text variables so that
     * layout has access to write data to the liveData
     *  object and thus enabling two way data Binding
     */

    //Edit Text content for shoe name
    var shoeName = MutableLiveData<String>()

    //Edit Text content for shoe size
    var shoeSize = MutableLiveData<Double>()

    //Edit text content for shoe Description
    var shoeDescription = MutableLiveData<String>()


    //Edit text content for shoe Company
    var shoeCompany = MutableLiveData<String>()


    val shoes: MutableList<Shoe> = mutableListOf()

    fun addNewShoe() {
        val shoe = Shoe(
            shoeName.value.toString(), shoeSize.value!!.toDouble(),
            shoeCompany.value.toString(), shoeDescription.value.toString()
        )
        shoes.add(shoe)
        _shoeList.value = shoes
        _addedShoe.value = true
        invalidateCurrentEditTextFields()

    }

    fun onSaveButtonClicked() {
        _addedShoe.value = false
    }

    fun invalidateCurrentEditTextFields() {
        shoeName.value = null
        shoeSize.value = null
        shoeCompany.value = null
        shoeDescription.value = null
    }

}