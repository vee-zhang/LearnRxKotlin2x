package com.example.william.learnrxkotlin2x.pojo

import android.os.Parcel
import android.os.Parcelable
import com.chad.library.adapter.base.entity.IExpandable
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.william.learnrxkotlin2x.widget.OperatorAdapter

/**
 * Created by william on 2018/2/1.
 */
data class DataPojo(
		val list: List<OperatorType>
)
data class OperatorType(val name:String,val operatorList:List<Operator>) : MultiItemEntity, IExpandable<Operator> {

	var mExpandable = false

	override fun getSubItems(): List<Operator> {
		return operatorList
	}

	override fun isExpanded(): Boolean {
		return mExpandable
	}

	override fun getLevel(): Int {
		return OperatorAdapter.Level.Type.ordinal
	}

	override fun setExpanded(expanded: Boolean) {
		this.mExpandable = expanded
	}

	override fun getItemType(): Int {
		return OperatorAdapter.Level.Type.ordinal
	}
}

data class Operator(val name:String, val fragmentName:String, val funcList:ArrayList<FunPojo>) : MultiItemEntity{

	override fun getItemType(): Int {
		return OperatorAdapter.Level.Operator.ordinal
	}
}

data class FunPojo(val name:String,val func:String):Parcelable {

	constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString())

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeString(func)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<FunPojo> {
		override fun createFromParcel(parcel: Parcel): FunPojo {
			return FunPojo(parcel)
		}

		override fun newArray(size: Int): Array<FunPojo?> {
			return arrayOfNulls(size)
		}
	}
}