package com.naughtsmt.lintu.data.data_source.dtoOld

data class HistoricalLowPrice(
    val country: String,
    val date: String,
    val isLow: Boolean,
    val price: Double
)