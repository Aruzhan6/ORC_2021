package com.example.meirlen.orc.view.adapter

import android.content.Context
import android.graphics.Color
import android.support.annotation.MainThread
import android.support.annotation.UiThread
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.meirlen.orc.R
import com.github.chuross.library.ExpandableLayout
import kotlinx.android.synthetic.main.item_category.view.*
import android.support.v4.view.ViewCompat.animate
import android.R.attr.scaleY
import android.R.attr.scaleX
import android.os.Handler
import com.example.meirlen.orc.helper.Constans
import com.example.meirlen.orc.helper.ImageLoader
import com.example.meirlen.orc.model.Category
import javax.inject.Inject


class CategoryItem(context: Context) : ConstraintLayout(context) {


    private var onProductVariantClickListener: OnProductVariantClickListener? = null


    init {
        View.inflate(context, R.layout.item_category, this)
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setData(category: Category) {
        titleText.text = category.categoryName

        if(category.categoryIcon!=null){
            ImageLoader.getInstance().load(context, category.categoryIcon, ic_logo)

        }

        if (category.children != null) {
            setChildCategories(category.children, linearLayoutItems)
        }
        btnExpand.setOnClickListener {
            expand(this)
        }
    }


    private fun setChildCategories(categories: List<Category>, view: ViewGroup) {
        view.removeAllViews()
        categories.forEach { it ->
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val child = inflater.inflate(R.layout.item_child_category, null)
            child.titleText.text = it.categoryName
            if (it.children.isNotEmpty()) {
                setChildCategories(it.children, child.linearLayoutItems)
            } else {
                child.btnExpand.visibility = View.GONE
                val category = it
                child.setOnClickListener { onProductVariantClickListener?.onProductVariantClicked(category) }
            }
            child.btnExpand.setOnClickListener {
                expand(child, false)
            }
            view.addView(child)
        }
    }

    private fun expand(view: View, mutable: Boolean = true) {
        if (view.expandableLayout.isExpanded) {
            view.expandableLayout.collapse()
            view.btnExpand.animate().rotation(90f).start()
            if (mutable) {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                view.titleText.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        } else {
            view.expandableLayout.expand()
            view.btnExpand.animate().rotation(270f).start()
            if (mutable) {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                view.titleText.setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }
    }

    fun setOnProductVariantClickListener(onProductVariantClickListener: OnProductVariantClickListener) {
        this.onProductVariantClickListener = onProductVariantClickListener
    }

    interface OnProductVariantClickListener {
        fun onProductVariantClicked(category:Category)
    }


}