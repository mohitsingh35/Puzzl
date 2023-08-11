package com.ncs.guessr.repository

import android.net.Uri
import com.ncs.guessr.firebase.RealTimeModelResponse
import com.ncs.guessr.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface RealtimeRepository {
    fun getItems(childName: String): Flow<ResultState<List<RealTimeModelResponse>>>

}