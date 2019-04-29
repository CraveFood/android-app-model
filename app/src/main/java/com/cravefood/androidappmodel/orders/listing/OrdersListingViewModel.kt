package com.cravefood.androidappmodel.orders.listing

import androidx.lifecycle.ViewModel
import com.cravefood.data.OrderList

class OrdersListingViewModel : ViewModel() {
	val model = OrdersListingModel()

	fun getOrders(query: String? = null) {
		model.recyclerViewOrdersListingOb.value = OrderList.getOrdersListing(query)
	}

}