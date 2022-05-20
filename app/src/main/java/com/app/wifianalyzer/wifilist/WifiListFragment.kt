package com.app.wifianalyzer.wifilist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.wifianalyzer.R
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
        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(WifiListViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}