package com.example.catalog.presentation

import android.content.Context
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.catalog.R

class ImageAdapter(
    private val context: Context,
    private val image: Int
) : PagerAdapter() {

    private val doubleArray = arrayOf(image, image)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.setImageResource(doubleArray[position])
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        container.addView(imageView)
        return imageView
    }
    override fun getCount(): Int {
        return doubleArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}