package com.naughtsmt.lintu.data.repository.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.naughtsmt.lintu.common.Constants.BGA_TABLE
import com.naughtsmt.lintu.data.data_source.remote.dto.PrimaryDesigner
import com.naughtsmt.lintu.data.data_source.remote.dto.PrimaryPublisher
//import kotlinx.serialization.Serializable

//TODO create a data converter
//@Serializable
@Entity(tableName = BGA_TABLE)
data class Game(
    val average_learning_complexity: Double,
    val average_strategy_complexity: Double,
    val average_user_rating: Double,
    @Embedded
    val categories: List<String>,
    val description: String,
    val description_preview: String,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image_url: String,
//    val images: ImagesX,
    val max_players: Int,
    val max_playtime: Double,
    @Embedded
    val mechanics: List<String>,
    val min_age: Int,
    val min_players: Int,
    val min_playtime: Double,
    val name: String,
    val official_url: String,
    @Embedded
    val primary_designer: PrimaryDesigner?,
    @Embedded
    val primary_publisher: PrimaryPublisher?,
    val rank: Double,
    val rules_url: String?,
    @Embedded
    val tags: List<String>,
    val thumb_url: String?,
    val type: String,
    val url: String,
    val year_published: Int
)
