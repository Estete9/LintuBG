package com.naughtsmt.lintu.data.repository.model

import com.naughtsmt.lintu.data.data_source.dto.ImagesX
import com.naughtsmt.lintu.data.data_source.dto.PrimaryDesigner
import com.naughtsmt.lintu.data.data_source.dto.PrimaryPublisher

//TODO create a data converter
data class Game(
//    val ownership: Boolean,
    val average_learning_complexity: Double,
    val average_strategy_complexity: Double,
    val average_user_rating: Double,
    val categories: List<String>,
    val description: String,
    val description_preview: String,
    val id: String,
    val image_url: String,
    val images: ImagesX,
    val max_players: Int,
    val max_playtime: Double,
    val mechanics: List<String>,
    val min_age: Int,
    val min_players: Int,
    val min_playtime: Double,
    val name: String,
    val official_url: String,
    val primary_designer: PrimaryDesigner?,
    val primary_publisher: PrimaryPublisher?,
    val rank: Double,
    val rules_url: String?,
    val tags: List<String>,
    val thumb_url: String?,
    val type: String,
    val url: String,
    val year_published: Int
)
