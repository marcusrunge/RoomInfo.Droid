package com.marcusrunge.roominfo.adapter

import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import java.io.InputStream

object ImageViewBindingsAdapter {
    @BindingAdapter("setFilePath")
    @JvmStatic
    fun bindFilePath(imageView: ImageView, filePath: String?) {
        if (filePath != null) {
            val fileUri = Uri.parse(filePath)
            val inputStream: InputStream? =
                imageView.context?.contentResolver?.openInputStream(fileUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageView.setImageBitmap(bitmap)
        }
    }
}