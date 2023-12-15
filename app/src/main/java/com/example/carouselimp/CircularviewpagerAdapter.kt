package com.example.carouselimp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide

class CircularviewpagerAdapter(private val context: Context,private val items:List<String>):PagerAdapter() {
    override fun getCount(): Int {
        return items.size*2
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater=LayoutInflater.from(context)
        val layout=inflater.inflate(R.layout.carouselitem,container,false)
        val itemposition=position%items.size
        val currentitem=items[itemposition]
        val imageview=layout.findViewById<ImageView>(R.id.imvforcarousel)
        Glide.with(context).load(currentitem).placeholder(R.color.black).into(imageview);
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        container.removeView(`object` as View)
    }


}