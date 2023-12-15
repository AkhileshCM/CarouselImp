package com.example.carouselimp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carouselimp.Retrofit.CarouselImages
import com.example.carouselimp.Retrofit.retroInterface
import com.example.carouselimp.Retrofit.retroObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.create

class Mainviewholder:ViewModel() {
    val apiinit = retroObj.getinstance().create(retroInterface::class.java)

    //    val imglinks= MutableLiveData<MutableList<String>>()
    var linkData: MutableLiveData<MutableList<String>> = MutableLiveData()

    //        get()=imglinks
    var linklist = mutableListOf<String>()
    val urllinks: MutableLiveData<MutableList<String>>
        get() = MutableLiveData()


    init {
        apicall()
    }

     fun apicall() {
        viewModelScope.launch {
            try {
                val response= withContext(Dispatchers.IO){
                    apiinit.getTest()
                }
                Log.d("nb","${response.body()}")
                val url= response.body()?.movies?.map { it.posterUrl } ?: emptyList()
                withContext(Dispatchers.Main){
                    Log.d("qaz","${linklist}")
                    linkData.setValue(updateurllinks(url))
                }
                //val currentlist=imglinks.value?.toMutableList()
//                val url= response.body()?.movies?.map { it.posterUrl } ?: emptyList()
//                Log.d("&&&","$url")
//                if(url.isNotEmpty()){
//                    updateurllinks(url)
//                }




            }
            catch (e:Exception){
                Log.d("error","error in response fetching $e")
            }
        }
    }

     fun updateurllinks(list: List<String>): MutableList<String> {
        val currentitem=urllinks.value?: mutableListOf()
        currentitem.addAll(list)
        urllinks.value=currentitem
        for( i in list){
            linklist.add(i)
            Log.d("QWER","${i}")

        }

        Log.d("@@@","${linklist}")
        return linklist





    }



}