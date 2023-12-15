package com.example.carouselimp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class ViewpagerAdapter(
    val context: Context,
    carouselLinks: MutableList<String>
):RecyclerView.Adapter<viewholder>() {

    val newlist= listOf(carouselLinks[carouselLinks.size-1])+carouselLinks+ listOf<String>(carouselLinks[0])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val inflater=LayoutInflater.from(parent.context)
        val viewForViewpager=inflater.inflate(R.layout.carouselitem,parent,false)
        return viewholder(viewForViewpager)
    }

    override fun getItemCount(): Int {

        return newlist.size

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val p=position%newlist.size
        val link=newlist[p]
        Log.d("sizeeeeeeee", link)
        Glide.with(context).load(link).placeholder(R.color.black).into(holder.viewpager);
    }

}

class viewholder(val view: View):ViewHolder(view){
    val viewpager=view.findViewById<ImageView>(R.id.imvforcarousel)


}