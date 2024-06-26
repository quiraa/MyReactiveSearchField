package com.quiraadev.myreactivesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.quiraadev.myreactivesearch.databinding.ActivityMainBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class MainActivity : AppCompatActivity(R.layout.activity_main) {
	private val binding by viewBinding(ActivityMainBinding::bind)

	val viewModel: MainViewModel by viewModels()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding.edPlace.addTextChangedListener(object : TextWatcher {
			override fun afterTextChanged(s: Editable?) {
			}

			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				lifecycleScope.launch {
					viewModel.queryChannel.value = s.toString()
				}
			}
		})

		viewModel.searchResult.observe(this, Observer { placesItem ->
			val placesName = placesItem.map { it.placeName }
			val adapter = ArrayAdapter(this@MainActivity, android.R.layout.select_dialog_item, placesName)
			adapter.notifyDataSetChanged()
			binding.edPlace.setAdapter(adapter)
		})
	}
}