package com.cravefood.androidappmodel.orders.listing

import androidx.lifecycle.MutableLiveData
import com.cravefood.data.OrderModel

class OrdersListingModel {
	val recyclerViewOrdersListingOb =
		MutableLiveData<List<OrderModel>>().apply { value = emptyList() }
}