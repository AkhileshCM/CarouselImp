package com.example.carouselimp

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs

class CarouselView: FrameLayout, ViewModelStoreOwner {
    private lateinit var Viewpager:ViewPager2
    private lateinit var tablayout:TabLayout
    private val CarouselInitialValue=1
    constructor(context: Context):super(context){
        initialize()
    }
    constructor(context: Context,attrs:AttributeSet?):super(context,attrs){
        initialize()
    }
    constructor(context: Context,attrs: AttributeSet?,defstyle:Int):super(context,attrs,defstyle){
        initialize()
    }
    private fun initialize(){
        inflate(context,R.layout.carouselview,this)
        Viewpager=findViewById(R.id.carouselViewpager)
        tablayout=findViewById(R.id.carouselTabLayout)
        val viewModel= ViewModelProvider(this)[SecondActivityViewModel::class.java]
        var passList: MutableList<String>? = null
        Viewpager.apply {
            clipToPadding=false
            clipChildren=false
            (getChildAt(0) as RecyclerView).overScrollMode= RecyclerView.OVER_SCROLL_NEVER
        }
        val compositePageTransformer=CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((20*Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->

            val r=1- abs(position)
            page.scaleY=.80f+r*0.20f
        }
        Viewpager.setPageTransformer(compositePageTransformer)


        Viewpager.setPadding(200, 0, 200, 0)


        viewModel.Imagelinks.observe(context as LifecycleOwner) {
            Log.e("PassListDataaaa", it.size.toString())
            //passList.add(it)

            passList = it.subList(5, 9)
            Viewpager.offscreenPageLimit= passList!!.size
            val adpater=ViewpagerAdapter(context, passList!!)
            Viewpager.adapter = adpater
            Viewpager.setCurrentItem(1, false)


//            Viewpager.setPageTransformer { page, position ->
//                val r = 1 - abs(position)
//                page.scaleY = .80f + r * 0.20f
//            }
            // Log.d("PassList","$passList" )
            Viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    if (state == ViewPager2.SCROLL_STATE_IDLE) {
                        when (Viewpager.currentItem) {
                            (passList?.size ?: 0)->{

                            }
                            (passList?.size ?: 0) + 1 -> {

//                                updatelist(passList!!,adpater)

                                Viewpager.setCurrentItem(1, false)
                            }

                            0 -> {
                                Viewpager.setCurrentItem(passList!!.size, false)

                            }
                        }
                    }

                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d("pixelselect","${position}")


                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    viewModel.updatecurrentposition(position)
                    addtostack(position)

                }
            })
            TabLayoutMediator(tablayout, Viewpager) { tab, position ->
                Log.d("positiontab","$tab")

            }.attach()

        }



    }
    fun addtostack(position: Int) {


    }
    fun updatelist(passList: MutableList<String>, adpater: ViewpagerAdapter) {
        passList.add(passList[0])
        adpater.notifyDataSetChanged()
        Log.d("newwww","${1%5}")

    }

    override val viewModelStore: ViewModelStore
        get() = ViewModelStore()

}