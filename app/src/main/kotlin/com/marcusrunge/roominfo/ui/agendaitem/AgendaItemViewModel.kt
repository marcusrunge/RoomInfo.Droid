package com.marcusrunge.roominfo.ui.agendaitem

import android.content.Context
import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.ui.ViewModelBase
import javax.inject.Inject

class AgendaItemViewModel @Inject constructor(
    private val context: Context,
    private val data: Data
) : ViewModelBase()