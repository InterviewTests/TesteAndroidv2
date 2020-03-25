package com.example.ibm_test.component

import android.view.View
import com.example.ibm_test.data.UserItemData
import com.example.ibm_test.utils.toMoney
import com.example.ibm_test.utils.toShortDate
import kotlinx.android.synthetic.main.user_item_view.view.*


class UserItemViewHolder(view: View) : CustomViewHolder<UserItemData>(view){
    private val titleItem = view.title_item
    private val dateItem = view.date_item
    private val descItem = view.desc_item
    private val valueItem = view.value_item

    override fun buildView(recyclerViewItem: AdapterItem<UserItemData>?) {
        titleItem.text = get()?.title
        dateItem.text = get()?.date?.toShortDate()
        descItem.text = get()?.desc
        valueItem.text = get()?.value?.toMoney()
    }
}