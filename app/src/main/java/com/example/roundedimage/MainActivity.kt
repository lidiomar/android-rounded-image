package com.example.roundedimage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.media.ThumbnailUtils
import android.os.Bundle
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.app.AppCompatActivity
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1)
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if(resultCode == Activity.RESULT_OK) {
            val data = resultData!!.data
            val inputStream = contentResolver.openInputStream(data)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            val centerCropBitmap = ThumbnailUtils.extractThumbnail(bitmap, 150, 150)
            val roundedBitmap = RoundedBitmapDrawableFactory.create(resources, centerCropBitmap)
            roundedBitmap.isCircular = true
            roundedBitmap.isFilterBitmap = true
            roundedBitmap.setAntiAlias(true)

            original.setImageDrawable(roundedBitmap)
            original.visibility = VISIBLE

        }
    }
}