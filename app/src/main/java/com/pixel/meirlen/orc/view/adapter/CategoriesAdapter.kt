package com.pixel.meirlen.orc.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.pixel.meirlen.orc.base.recyclerview.BaseRecyclerAdapter
import com.pixel.meirlen.orc.base.recyclerview.OnItemClickListener
import com.pixel.meirlen.orc.model.Category


class CategoriesAdapter(dataList: List<Category>, private val onOrderItemClickListener: OnOrderItemClickListener
) :
        BaseRecyclerAdapter<Category>(dataList, onOrderItemClickListener) {


    override fun getItemHolder(context: Context, viewType: Int): RecyclerView.ViewHolder {
        return OrderViewHolder(getItemView(context, viewType) as CategoryItem, onOrderItemClickListener)
    }

    override fun getItemView(context: Context, viewType: Int): View {
        return CategoryItem(context)
    }

    override fun bindData(itemView: View, data: Category, position: Int) {
        if (itemView is CategoryItem) {
            itemView.setData(data)

        }
    }

    private class OrderViewHolder(orderView: CategoryItem, onOrderItemClickListener: OnOrderItemClickListener?) :
            RecyclerView.ViewHolder(orderView) {

        init {
            orderView.setOnClickListener {
                onOrderItemClickListener?.onItemClicked(adapterPosition)
            }
            orderView.setOnProductVariantClickListener(object : CategoryItem.OnProductVariantClickListener {
                override fun onProductVariantClicked(category: Category) {
                    onOrderItemClickListener?.onProductVariantClicked(category)
                }
            })
        }
    }

    interface OnOrderItemClickListener : OnItemClickListener {

        fun onProductVariantClicked(category: Category)
    }


}