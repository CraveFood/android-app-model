package com.cravefood.androidappmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.fragment_start_screen.*

class StartScreenFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = inflater.inflate(R.layout.fragment_start_screen, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

//		materialButton01.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.screenOneFragment, null))
		materialButton01.setOnClickListener { view ->
			findNavController(view).navigate(R.id.action_startScreenFragment_to_screenOneFragment)
		}
	}
}
