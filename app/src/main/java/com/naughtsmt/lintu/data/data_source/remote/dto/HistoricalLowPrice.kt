package com.naughtsmt.lintu.data.data_source.remote.dto

data class HistoricalLowPrice(
    val country: String,
    val date: String,
    val isLow: Boolean,
    val price: String
)