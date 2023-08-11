package com.ncs.guessr.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.ncs.guessr.firebase.RealTimeModelResponse
import com.ncs.guessr.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RealtimeDBRepository @Inject constructor(
    private val db: DatabaseReference
):RealtimeRepository {
    override fun getItems(childName: String): Flow<ResultState<List<RealTimeModelResponse>>> = callbackFlow{
        trySend(ResultState.Loading)

        val valueEvent=object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items=snapshot.children.map {
                    RealTimeModelResponse(
                        it.getValue(RealTimeModelResponse.RealTimeItems::class.java),
                        key = it.key
                    )
                }
                trySend(ResultState.Success(items))
                Log.d("pari",items.toString())
            }


            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }

        }
        db.child("questions").child(childName).addValueEventListener(valueEvent)
        awaitClose{
            db.child("questions").child(childName).removeEventListener(valueEvent)
            close()
        }
    }

}