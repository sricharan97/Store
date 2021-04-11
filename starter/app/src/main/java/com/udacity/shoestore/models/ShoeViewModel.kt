package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel() : ViewModel() {

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    private val _onSave = MutableLiveData<Boolean>()
    val onSave: LiveData<Boolean>
        get() = _onSave

    private val _onCancel = MutableLiveData<Boolean>()
    val onCancel: LiveData<Boolean>
        get() = _onCancel

    /** Expose MutableLiveData for edit text variables so that
     * layout has access to write data to the liveData
     *  object and thus enabling two way data Binding
     */

    //Edit Text content for shoe name
    val shoeName = MutableLiveData<String>()

    //Edit Text content for shoe size
    val shoeSize = MutableLiveData<Double>()

    //Edit text content for shoe Description
    val shoeDescription = MutableLiveData<String>()


    //Edit text content for shoe Company
    val shoeCompany = MutableLiveData<String>()


    val shoes: MutableList<Shoe> = mutableListOf()

    init {
        _onSave.value = false
        _onCancel.value = false
    }

    fun addNewShoe() {
        val shoe = Shoe(
                shoeName.value.toString(), shoeSize.value!!.toDouble(),
                shoeCompany.value.toString(), shoeDescription.value.toString()
        )
        shoes.add(shoe)
        _shoeList.value = shoes
        _onSave.value = true
        invalidateCurrentEditTextFields()

    }

    fun doneSaving() {
        _onSave.value = false
    }

    fun clicksCancel() {
        _onCancel.value = true
        invalidateCurrentEditTextFields()
    }

    fun doneCancelling() {
        _onCancel.value = false
    }

    fun invalidateCurrentEditTextFields() {
        shoeName.value = null
        shoeSize.value = null
        shoeCompany.value = null
        shoeDescription.value = null

    }

}