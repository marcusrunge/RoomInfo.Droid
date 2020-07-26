package com.marcusrunge.roominfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.marcusrunge.roominfo.databinding.ActivityMainBinding
import com.marcusrunge.roominfo.interfaces.OnBackClickedListener
import com.marcusrunge.roominfo.ui.main.MainViewModel
import dagger.android.AndroidInjection
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val onBackClickedListeners: MutableList<WeakReference<OnBackClickedListener>> =
        mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        val viewmodel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding.viewmodel = viewmodel
    }

    fun addOnBackClickedListener(onBackClickedListener: OnBackClickedListener) {
        onBackClickedListeners.add(WeakReference(onBackClickedListener))
    }

    fun removeOnBackClickedListener(onBackClickedListener: OnBackClickedListener) {
        val iterator: MutableIterator<WeakReference<OnBackClickedListener>> =
            onBackClickedListeners.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<OnBackClickedListener> = iterator.next()
            if (weakRef.get() === onBackClickedListener) {
                iterator.remove()
            }
        }
    }

    override fun onBackPressed() {
        for (weakRef in onBackClickedListeners) {
            try {
                weakRef.get()?.onBackClicked()
            } catch (e: Exception) {
            }
        }
        super.onBackPressed()
    }
}
