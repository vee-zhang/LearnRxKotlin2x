package com.example.william.learnrxkotlin2x.catalog


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.william.learnrxkotlin2x.R


/**
 * A simple [Fragment] subclass.
 */
class SchedulersFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedulers, container, false)
    }

}// Required empty public constructor
