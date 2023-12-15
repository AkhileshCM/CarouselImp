package com.example.carouselimp.Retrofit

data class CarouselImages(
    val apiMapping: List<ApiMapping>,
    val content: List<Content>,
    val totalElements: Int,
    val totalPages: Int
) {
    data class ApiMapping(
        val method: String,
        val timeTaken: Int,
        val timestamp: Long,
        val url: String
    )

    data class Content(
        val actor: List<Actor>,
        val availableDays: Any,
        val availableOn: Int,
        val availableTill: Long,
        val categoryId: List<String>,
        val contentGuid: String,
        val countries: List<String>,
        val description: String,
        val director: List<Director>,
        val displayDuration: Int,
        val downloadUrl: String,
        val episodeNumber: Any,
        val genre: List<String>,
        val images: List<Image>,
        val isCastable: Boolean,
        val isCcAvailable: Boolean,
        val isDownloadable: Boolean,
        val maxQualityAvailable: String,
        val mediaGuid: String,
        val parentalControl: List<ParentalControl>,
        val purchaseMode: String,
        val releaseYear: Int,
        val seasonUid: String,
        val streams: String,
        val studio: List<String>,
        val title: String,
        val trailers: List<Trailer>,
        val type: String,
        val uid: String
    ) {
        data class Actor(
            val creditType: String,
            val personId: String,
            val personName: String
        )

        data class Director(
            val creditType: String,
            val personId: String,
            val personName: String
        )

        data class Image(
            val height: Int,
            val type: String,
            val url: String,
            val width: Int
        )

        data class ParentalControl(
            val rating: String
        )

        data class Trailer(
            val streams: String
        )
    }
}