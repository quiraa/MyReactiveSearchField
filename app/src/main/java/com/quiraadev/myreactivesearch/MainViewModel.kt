package com.quiraadev.myreactivesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.quiraadev.myreactivesearch.network.ApiConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest


@OptIn(ExperimentalCoroutinesApi::class)
@FlowPreview
class MainViewModel : ViewModel() {
	private val accessToken = "pk.eyJ1IjoiYXJpZmFpemluIiwiYSI6ImNrYTI2c3R1cjAwNXAzbm1zaDYyZW1ra2cifQ.okSWF0zf58rWkhoVuYjShQ"
	val queryChannel = MutableStateFlow("")

	val searchResult = queryChannel
		.debounce(300)
		.distinctUntilChanged()
		.filter {
			it.trim().isNotEmpty()
		}
		.mapLatest {
			ApiConfig.provideApiService().getCountry(it, accessToken).features
		}
		.asLiveData()
}