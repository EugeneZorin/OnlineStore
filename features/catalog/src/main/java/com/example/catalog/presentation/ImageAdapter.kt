package com.example.catalog.presentation

import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.catalog.R

class ImageAdapter(
    private val context: Context,
    private val image: Bitmap?
) : PagerAdapter() {

    private val doubleArray = arrayOf(image, image)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageBitmap(doubleArray[position])
        }
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