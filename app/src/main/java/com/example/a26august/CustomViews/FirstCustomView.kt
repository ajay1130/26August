package com.example.a26august.CustomViews

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.a26august.R

class FirstCustomView(contextApp:Context, attrs:AttributeSet):LinearLayout(contextApp,attrs) {

    private val mAttributes:TypedArray
    private val mImageView:ImageView
    private val mTextView:TextView
    init {
        inflate(contextApp, R.layout.cutsom_view,this)
        mAttributes = contextApp.obtainStyledAttributes(attrs,R.styleable.FirstCustomView)
        mImageView = findViewById(R.id.image_customview)
        mTextView = findViewById(R.id.text_custom_view)
        mImageView.setImageResource(mAttributes.getResourceId(R.styleable.FirstCustomView_image,R.color.black))
        mAttributes.getString(R.styleable.FirstCustomView_text)?.let { setText(it) }
    }
    private fun setText(text:String){
        mTextView.text = text
    }

}