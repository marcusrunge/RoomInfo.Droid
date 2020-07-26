package com.marcusrunge.roominfo.ui.agendaitem

import com.marcusrunge.roominfo.data.interfaces.Data
import com.marcusrunge.roominfo.models.ApplicationResource
import com.marcusrunge.roominfo.ui.ViewModelBase
import javax.inject.Inject

class AgendaItemViewModel @Inject constructor(
    private val applicationResource: ApplicationResource,
    private val data: Data
) : ViewModelBase()