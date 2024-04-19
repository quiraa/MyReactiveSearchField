package com.quiraadev.myreactivesearch.network

import com.quiraadev.myreactivesearch.model.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
	@GET("mapbox.places/{query}.json")
	suspend fun getCountry(
		@Path("query") query: String,
		@Query("access_token") accessToken: String,
		@Query("autocomplete") autoComplete: Boolean = true
	): PlaceResponse
}