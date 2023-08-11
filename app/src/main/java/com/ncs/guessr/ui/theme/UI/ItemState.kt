package com.ncs.guessr.ui.theme.UI

import com.ncs.guessr.firebase.RealTimeModelResponse

data class ItemState(
    val item:List<RealTimeModelResponse> = emptyList(),
    val error:String = "",
    val isLoading:Boolean=false
)