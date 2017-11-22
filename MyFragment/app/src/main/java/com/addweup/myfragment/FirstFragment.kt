package com.addweup.myfragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 */

class FirstFragment : Fragment() {

    companion object {
        fun newInstance(number:Int):FirstFragment{
            val fragment = FirstFragment()
            fragment.number = number
            return fragment
        }
    }

    private var holder:Holder? = null
    private var mainActivity: MainActivity? = null
    private var number = 9527

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as? MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_first, container, false)
        holder = Holder(root)
        holder?.textView?.text = "This is fragment ${number}."
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        holder?.nextButton?.setOnClickListener({ mainActivity?.nextFragment(newInstance(number+1)) })
        holder?.prevButton?.setOnClickListener({ mainActivity?.prevFragment() })
    }

    class Holder{
        constructor(root:View){
            nextButton = root.findViewById(R.id.nextButton)
            prevButton = root.findViewById(R.id.previousButton)
            textView = root.findViewById(R.id.textView)
        }

        var nextButton: Button
        var prevButton: Button
        var textView: TextView
    }
}
