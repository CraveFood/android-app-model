package com.cravefood.data

data class ProductModel(
	val id: Long,
	val name: String,
	val photo: String,
	val description: String,
	val priceLabel: String,
	val price: Double,
	val unit: String
)