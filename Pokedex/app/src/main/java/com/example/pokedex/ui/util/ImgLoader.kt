package com.example.pokedex.ui.util

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokedex.R
import com.example.pokedex.data.api.ApiDefaultValues

class ImgLoader(
    private val glide: RequestManager,
    private val cache: DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC
) {
    fun loadImageById(imgView: ImageView, imgError: Int = R.drawable.img_not_found,
                        id: Int){
        glide
            .load(ApiDefaultValues.getImgUrlById(id))
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(cache)
            .error(imgError)
            .skipMemoryCache(true)
            .into(imgView);
    }
}