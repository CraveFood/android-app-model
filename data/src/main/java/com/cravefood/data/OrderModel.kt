package com.cravefood.data

data class OrderModel(
	val id: Long,
	val purchaseDate: String,
	val purchaserName: String,
	val purchaserPhoto: String,
	val vendorName: String,
	val vendorPhoto: String,
	val totalPriceLabel: String,
	val totalPrice: Double,
	val totalItems: Int,
	val totalItemsLabel: String
)