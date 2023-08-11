package com.ncs.guessr.firebase

data class RealTimeModelResponse(
    val item:RealTimeItems?,
    val key:String?="",

    ){

    data class RealTimeItems(

        val answer:String?="",
        val image:Map<String,String> = emptyMap(),
        val options: List<String?> = emptyList(),
    )
}