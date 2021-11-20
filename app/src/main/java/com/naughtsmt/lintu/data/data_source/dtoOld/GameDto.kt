package com.naughtsmt.lintu.data.data_source.dtoOld

import com.naughtsmt.lintu.data.repository.model.Game

data class GameDto(
    val active: Boolean,
    val amazon_rank: Int,
    val artists: List<String>,
    val availability_status: String,
    val average_learning_complexity: Double,
    val average_strategy_complexity: Double,
    val average_user_rating: Double,
    val categories: List<Category>,
    val comment_count: Int,
    val commentary: String,
    val description: String,
    val description_preview: String,
    val designers: List<Designer>,
    val developers: List<Any>,
    val discount: String,
    val edit_url: String,
    val faq: String,
    val handle: String,
    val historical_low_prices: List<HistoricalLowPrice>,
    val id: String,
    val image_url: String,
    val images: ImagesX,
    val is_historical_low: Boolean,
    val isbn: String,
    val links: Int,
    val listing_clicks: Int,
    val lists: Int,
    val matches_specs: Any,
    val max_players: Int,
    val max_playtime: Int,
    val mechanics: List<Mechanic>,
    val mentions: Int,
    val min_age: Int,
    val min_players: Int,
    val min_playtime: Int,
    val msrp: Int,
    val msrp_text: String,
    val msrps: List<Msrp>,
    val name: String,
    val names: List<Any>,
    val num_distributors: Int,
    val num_user_complexity_votes: Int,
    val num_user_ratings: Int,
    val official_url: String,
    val plays: Int,
    val price: String,
    val price_au: String,
    val price_ca: String,
    val price_text: String,
    val price_uk: String,
    val primary_designer: PrimaryDesigner,
    val primary_publisher: PrimaryPublisher,
    val publishers: List<Publisher>,
    val rank: Int,
    val related_to: List<Any>,
    val rules_url: String,
    val sell_sheet_url: Any,
    val size_height: Int,
    val sku: String,
    val specs: List<Any>,
    val store_images_url: Any,
    val tags: List<String>,
    val thumb_url: String,
    val trending_rank: Int,
    val type: String,
    val upc: String,
    val url: String,
    val video_links: List<Any>,
    val visits: Int,
    val year_published: Int
)

//fun GameDto.toGame(): Game {
//    return Game(
//        average_learning_complexity = average_learning_complexity,
//        average_strategy_complexity = average_strategy_complexity,
//        average_user_rating = average_user_rating,
////        TODO find a way to get the categories of each game and display them in their game list/game details
//        categories = categories.map { it.id },
//        description = description,
//        description_preview = description_preview,
//        id = id,
//        image_url = image_url,
//        images = images,
//        max_players = max_players,
//        max_playtime = max_playtime,
////        TODO find a way to get the mechanics of each game and display them in their game list/game details
//        mechanics = mechanics.map { it.id },
//        min_age = min_age,
//        min_players = min_players,
//        min_playtime = min_playtime,
//        name = name,
//        official_url = official_url,
//        primary_designer = primary_designer,
//        primary_publisher = primary_publisher,
//        rank = rank,
//        rules_url = rules_url,
//        tags = tags,
//        thumb_url = thumb_url,
//        type = type,
//        url = url,
//        year_published = year_published
//    )
//}