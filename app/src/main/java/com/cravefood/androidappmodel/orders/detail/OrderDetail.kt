package com.cravefood.androidappmodel.orders.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.observe
import com.cravefood.data.OrderModel
import kotlinx.android.synthetic.main.fragment_order_detail.*
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
		observe(viewModel.model.orderModel, ::handleOrderModel)
	}

	private fun initScreen() {
		viewModel.getOrderByDefault(arguments?.getLong(getString(R.string.argument_order_detail_orderId)))
	}

	private fun handleOrderModel(order: OrderModel?) {
		Glide
			.with(this)
			.load(order?.vendorPhoto)
			.centerCrop()
			.into(imageViewOrderDetailVendorLogo)

		textViewOrderDetailVendorName.text = order?.vendorName
		textViewOrderDetailDate.text = order?.purchaseDate
		textViewOrderDetailTotalPrice.text = order?.totalPriceLabel
		textViewOrderDetailTotalItems.text = order?.totalItemsLabel

		setToolbarTitle(order)
	}

	private fun setToolbarTitle(order: OrderModel?) {
		if (activity != null && activity is AppCompatActivity) {
			(activity as AppCompatActivity).supportActionBar?.title =
				getString(R.string.order_var, order?.id.toString())
		}
	}

}
