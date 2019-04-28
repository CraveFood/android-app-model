package com.example.common

import android.content.Context
import androidx.appcompat.widget.SearchView
import com.cravefood.common.R
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import java.util.*

private const val DEBOUNCE_DELAY: Long = 1000 // milliseconds

abstract class DebounceQueryTextListener(val context: Context?) : SearchView.OnQueryTextListener {

	private var timer = Timer()

	abstract fun doOnTextChanged(text: String)

	override fun onQueryTextSubmit(query: String): Boolean {
		onQueryTextChange(query)
		return false
	}

	override fun onQueryTextChange(newText: String): Boolean {
		timer.cancel()
		timer = Timer()
		timer.schedule(
			object : TimerTask() {
				override fun run() {
					context?.runOnUiThread {
						if (newText.isNotEmpty() && newText.length < 2) {
							toast(context.getString(R.string.you_must_type_at_least_two_characters))
						} else {
							doOnTextChanged(newText)
						}
					}

				}
			},
			DEBOUNCE_DELAY
		)
		return false
	}
}