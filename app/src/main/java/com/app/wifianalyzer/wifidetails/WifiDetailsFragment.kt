package com.app.wifianalyzer.wifidetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.app.wifianalyzer.R
import com.app.wifianalyzer.databinding.FragmentWifiDetailsBinding

class WifiDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = WifiDetailsFragment()
    }

    private lateinit var viewModel: WifiDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentWifiDetailsBinding>(
            inflater, R.layout.fragment_wifi_list, container, false);
        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(WifiDetailsViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}