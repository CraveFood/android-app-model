package com.cravefood.androidappmodel.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cravefood.androidappmodel.R

class Settings : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_settings, container, false)
	}
}
