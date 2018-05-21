package com.example.william.learnrxkotlin2x


import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.william.learnrxkotlin2x.pojo.FunPojo
import com.example.william.learnrxkotlin2x.widget.NormalDecoration
import com.example.william.learnrxkotlin2x.widget.OperatorFunAdapter
import kotlinx.android.synthetic.main.fragment_base.view.*


/**
 * A simple [Fragment] subclass.
 */
abstract class BaseOperatorFragment : Fragment(),OperatorFunAdapter.OnItemClickListener {

    val TAG = this::class.java.simpleName

    private lateinit var pojoList:ArrayList<FunPojo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.pojoList = arguments["funcList"] as ArrayList<FunPojo>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_base, container, false)
        val adapter = OperatorFunAdapter(pojoList,this)
        rootView.rv.layoutManager = LinearLayoutManager(context)
        rootView.rv.adapter = adapter
        rootView.rv.addItemDecoration(NormalDecoration())
        return rootView
    }

    override fun onItemClick(func: String) {
//        this.javaClass.getDeclaredMethod(func,String::class.java)
        val m = this::class.java.getMethod(func)
        m.invoke(this)
    }

    fun log(methodName:String,s:String){
        Log.d(TAG,"-$s")
    }
    fun log(methodName:String,e:Throwable){
        Log.e(TAG,"-$methodName",e)
    }

    fun toast(s:String){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
    }
}
