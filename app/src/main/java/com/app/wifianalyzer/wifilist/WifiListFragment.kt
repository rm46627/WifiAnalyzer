package com.app.wifianalyzer.wifilist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.wifianalyzer.R
import com.app.wifianalyzer.database.WifiDatabase
import com.app.wifianalyzer.databinding.FragmentWifiListBinding

class WifiListFragment : Fragment() {

    companion object {
        fun newInstance() = WifiListFragment()
    }

    private lateinit var viewModel: WifiListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentWifiListBinding>(
            inflater, R.layout.fragment_wifi_list, container, false);

        val application = requireNotNull(this.activity).application
        val dataSource = WifiDatabase.getInstance(application).wifiScanDao
        val viewModelFactory = WifiListViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(WifiListViewModel::class.java)

        val adapter = WifiListAdapter()
        binding.wifiList.adapter = adapter

        viewModel.wifiListHistory.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

}