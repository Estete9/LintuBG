package com.naughtsmt.lintu.data.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.naughtsmt.lintu.common.Constants.BGA_REMOTE_KEYS_TABLE

@Entity(tableName = BGA_REMOTE_KEYS_TABLE)
data class BGARemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
