package com.marcusrunge.roominfo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.marcusrunge.roominfo.databinding.ActivityMainBinding
import com.marcusrunge.roominfo.interfaces.OnBackClickedListener
import com.marcusrunge.roominfo.interfaces.OnFileSelectedListener
import com.marcusrunge.roominfo.ui.main.MainViewModel
import com.marcusrunge.roominfo.ui.settings.SettingsFragment.Companion.SELECT_FILE_REQUEST_CODE
import dagger.android.AndroidInjection
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val onBackClickedListeners: MutableList<WeakReference<OnBackClickedListener>> =
        mutableListOf()
    private val onFileSelectedListeners: MutableList<WeakReference<OnFileSelectedListener>> =
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

    fun addOnFileSelectedListener(onFileSelectedListener: OnFileSelectedListener) {
        onFileSelectedListeners.add(WeakReference(onFileSelectedListener))
    }

    fun removeOnFileSelectedListener(onFileSelectedListener: OnFileSelectedListener) {
        val iterator: MutableIterator<WeakReference<OnFileSelectedListener>> =
            onFileSelectedListeners.iterator()
        while (iterator.hasNext()) {
            val weakRef: WeakReference<OnFileSelectedListener> = iterator.next()
            if (weakRef.get() === onFileSelectedListener) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                SELECT_FILE_REQUEST_CODE -> {
                    for (weakRef in onFileSelectedListeners) {
                        try {
                            weakRef.get()?.onFileSelected(data?.data)
                        } catch (e: Exception) {
                        }
                    }
                }
            }
        }
    }
}
