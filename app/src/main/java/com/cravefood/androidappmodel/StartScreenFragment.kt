package com.cravefood.androidappmodel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_start_screen.*
import java.util.*

class StartScreenFragment : Fragment(), TimePickerDialog.OnTimeSetListener {
	override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
		//
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = inflater.inflate(R.layout.fragment_start_screen, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val datePickerDialog = TimePickerDialog(context!!,
			object :TimePickerDialog.OnTimeSetListener{
				override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
					TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
				}
			}, 1, 0, false);
		datePickerDialog.show()

		materialButton01.setOnClickListener(
			Navigation.createNavigateOnClickListener(R.id.screenOneFragment, null)
		)

		materialButton02.setOnClickListener {
			Navigation.findNavController(view)
				.navigate(R.id.action_startScreenFragment_to_screenTwoFragment)
		}

		materialButton03.setOnClickListener {
			val bundle = bundleOf(getString(R.string.param_screen_two_fragment_name) to "Kennedy")
			Navigation.findNavController(view)
				.navigate(R.id.action_startScreenFragment_to_screenTwoFragment2, bundle)
		}
	}
}
