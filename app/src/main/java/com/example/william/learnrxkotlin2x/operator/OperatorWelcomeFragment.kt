package com.example.william.learnrxkotlin2x.operator


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.william.learnrxkotlin2x.R


/**
 * A simple [Fragment] subclass.
 */
class OperatorWelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_operator_welcome, container, false)
    }
}
