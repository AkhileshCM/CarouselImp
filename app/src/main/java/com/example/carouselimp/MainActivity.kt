package com.example.carouselimp

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.carouselimp.Retrofit.retroInterface
import com.example.carouselimp.Retrofit.retroObj
import kotlinx.coroutines.launch
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewmodel= ViewModelProvider(this)[Mainviewholder()::class.java]
        lifecycleScope.launch {
            val viewpager=findViewById<ViewPager2>(R.id.carouselViewpager)
            viewpager.setPadding(180,0,0,180)
            viewpager.apply {
                clipChildren=false
                clipToPadding=false
                (getChildAt(0) as RecyclerView).overScrollMode=RecyclerView.OVER_SCROLL_NEVER
            }
            val compositePageTransformer=CompositePageTransformer()

            compositePageTransformer.addTransformer(MarginPageTransformer((40*Resources.getSystem().displayMetrics.density).toInt()))
            compositePageTransformer.addTransformer { page, position ->
                val r=1- abs(position)
                page.scaleY=(.80f+r*0.20f)

            }
            viewpager.setPageTransformer(compositePageTransformer)






            viewmodel.linkData.observe(this@MainActivity){ data ->
                Log.d("anonyyy","${data}")
            }
//            viewpager.adapter=ViewpagerAdapter(this@MainActivity,)

            viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    if(state==ViewPager2.SCROLL_STATE_IDLE){
                        when(viewpager.currentItem){

                        }

                    }
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            })

        }


    }
}