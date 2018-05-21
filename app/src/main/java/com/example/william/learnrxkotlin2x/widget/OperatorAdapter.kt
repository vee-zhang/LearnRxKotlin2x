package com.example.william.learnrxkotlin2x.widget

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.william.learnrxkotlin2x.pojo.Operator
import com.example.william.learnrxkotlin2x.pojo.OperatorType
import com.example.william.learnrxkotlin2x.R

/**
 * Created by william on 2018/1/30.
 */
class OperatorAdapter(data: List<MultiItemEntity>, private var listener: OnItemClickListener) : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data as MutableList<MultiItemEntity>) {

    init {
        addItemType(Level.Type.ordinal, R.layout.item_group)
        addItemType(Level.Operator.ordinal, R.layout.item_item)
    }

    enum class Level {
        Type, Operator
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        when(helper.itemViewType){
            Level.Type.ordinal->{
                item as OperatorType
                helper.setText(R.id.tv,item.name)
                helper.itemView.setOnClickListener {
                    val pos = helper.getAdapterPosition()
                    if (item.isExpanded){
                        collapse(pos)
                    } else {
                        expand(pos)
                    }
                }
            }
            Level.Operator.ordinal->{
                val operator = item as Operator
                helper.setText(R.id.tv,operator.name)
                helper.itemView.setOnClickListener {
                    this.listener.onItemClick(operator)
                }
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(operator:Operator)
    }
}