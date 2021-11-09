package com.naughtsmt.lintu.domain.model

data class HistoricalLowPrice(
    val country: String,
    val date: String,
    val isLow: Boolean,
    val price: Double
)