package com.cravefood.androidappmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_screen_two.*

class ScreenTwoFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View =
		inflater.inflate(R.layout.fragment_screen_two, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		textView.text = arguments?.getString(getString(R.string.param_screen_two_fragment_name)) ?: ""
	}
}
