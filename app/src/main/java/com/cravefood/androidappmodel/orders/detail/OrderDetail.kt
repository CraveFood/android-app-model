package com.cravefood.androidappmodel.orders.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cravefood.androidappmodel.R
import com.cravefood.data.OrderModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderDetail : Fragment() {

	private val viewModel by viewModel<OrderDetailViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_order_detail, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		configureObservables()
		initScreen()
	}

	private fun configureObservables() {

	}

	private fun initScreen() {

	}

	private fun handleOrderModel(order: OrderModel?) {

	}

}
