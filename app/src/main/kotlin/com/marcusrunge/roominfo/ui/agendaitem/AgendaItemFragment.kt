package com.marcusrunge.roominfo.ui.agendaitem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.marcusrunge.roominfo.R
import com.marcusrunge.roominfo.databinding.FragmentAgendaitemBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AgendaItemFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Override
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAgendaitemBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_agendaitem, container, false)
        binding.lifecycleOwner = this
        val viewmodel = ViewModelProvider(this, viewModelFactory)[AgendaItemViewModel::class.java]
        binding.viewmodel = viewmodel
        return binding.root
    }
}