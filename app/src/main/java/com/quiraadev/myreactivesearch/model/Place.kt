package com.quiraadev.myreactivesearch.model

import com.google.gson.annotations.SerializedName

data class PlacesItem(
	@SerializedName("place_name")
	val placeName: String
)

data class PlaceResponse(
	@SerializedName("features")
	val features: List<PlacesItem>
)