package com.example.carouselimp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carouselimp.Retrofit.retroInterface
import com.example.carouselimp.Retrofit.retroObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create

class SecondActivityViewModel:ViewModel() {
    val apiinitilization=retroObj.getinstance().create(retroInterface::class.java)
    val Imagelinks= MutableLiveData<MutableList<String>>()
    val _Imagelinks= mutableListOf<String>()
    var currentposition= MutableLiveData<Int>(1)
    fun updatecurrentposition(value:Int){
        currentposition.value=value
    }
    init {
        apiCall()
    }
    private fun apiCall(){
        viewModelScope.launch {
            try {
                val response= withContext(Dispatchers.IO){
                    apiinitilization.getTest()
                }
                val imageurls=response.body()?.movies?.map { it.posterUrl }?: emptyList()
                Imagelinks.setValue(addLinks(imageurls))
            }
            catch (e:Exception){
                throw error("Cant get response $e")
            }
        }
    }
    private fun addLinks(links:List<String>): MutableList<String> {
        for (i in links){
            _Imagelinks.add(i)
        }
        return _Imagelinks
    }

}