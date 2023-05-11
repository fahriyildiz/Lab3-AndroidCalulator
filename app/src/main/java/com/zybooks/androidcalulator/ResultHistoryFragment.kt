package com.zybooks.androidcalulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ResultHistoryFragment : Fragment() {
    private val historyList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result_history, container, false)
        listView = view.findViewById(R.id.listView)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, historyList)
        listView.adapter = adapter
        return view
    }

    fun updateHistory(newResult: String) {
        historyList.add(newResult)
        adapter.notifyDataSetChanged()
    }
}