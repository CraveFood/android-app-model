package com.cravefood.androidappmodel.orders.detail

import androidx.lifecycle.ViewModel
import com.cravefood.data.OrderList

class OrderDetailViewModel : ViewModel() {

	val model = OrderDetailModel()

	fun getOrderByDefault(orderId: Long?) {
		if (orderId != null) model.orderModel.value = OrderList.getOrderById(orderId)
	}

}