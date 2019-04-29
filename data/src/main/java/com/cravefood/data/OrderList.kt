package com.cravefood.data

class OrderList {
	companion object {
		fun getOrdersListing(query: String? = null): List<OrderModel> {
			val list = listOf(
				OrderModel(
					id = 1,
					purchaseDate = "2019-01-01",
					purchaserName = "Kennedy Purchaser",
					purchaserPhoto = "https://img.icons8.com/color/420/person-male.png",
					vendorName = "Kennedy Vendor",
					vendorPhoto = "https://www.dicasnutricao.com.br/wp-content/uploads/2015/06/beneficios-da-laranja.jpg",
					totalPriceLabel = "$25.90",
					totalPrice = 25.90,
					totalItemsLabel = "10 items",
					totalItems = 10
				),
				OrderModel(
					id = 2,
					purchaseDate = "2019-01-01",
					purchaserName = "Kennedy Purchaser",
					purchaserPhoto = "https://img.icons8.com/color/420/person-male.png",
					vendorName = "Kennedy Vendor",
					vendorPhoto = "https://www.dicasnutricao.com.br/wp-content/uploads/2015/06/beneficios-da-laranja.jpg",
					totalPriceLabel = "$25.90",
					totalPrice = 25.90,
					totalItemsLabel = "10 items",
					totalItems = 10
				),
				OrderModel(
					id = 3,
					purchaseDate = "2019-01-01",
					purchaserName = "Kennedy Purchaser",
					purchaserPhoto = "https://img.icons8.com/color/420/person-male.png",
					vendorName = "Kennedy Vendor",
					vendorPhoto = "https://www.dicasnutricao.com.br/wp-content/uploads/2015/06/beneficios-da-laranja.jpg",
					totalPriceLabel = "$25.90",
					totalPrice = 25.90,
					totalItemsLabel = "10 items",
					totalItems = 10
				),
				OrderModel(
					id = 4,
					purchaseDate = "2019-01-01",
					purchaserName = "Kennedy Purchaser",
					purchaserPhoto = "https://img.icons8.com/color/420/person-male.png",
					vendorName = "Kennedy Vendor",
					vendorPhoto = "https://www.dicasnutricao.com.br/wp-content/uploads/2015/06/beneficios-da-laranja.jpg",
					totalPriceLabel = "$25.90",
					totalPrice = 25.90,
					totalItemsLabel = "10 items",
					totalItems = 10
				),
				OrderModel(
					id = 5,
					purchaseDate = "2019-01-01",
					purchaserName = "Kennedy Purchaser",
					purchaserPhoto = "https://img.icons8.com/color/420/person-male.png",
					vendorName = "Kennedy Vendor",
					vendorPhoto = "https://www.dicasnutricao.com.br/wp-content/uploads/2015/06/beneficios-da-laranja.jpg",
					totalPriceLabel = "$25.90",
					totalPrice = 25.90,
					totalItemsLabel = "10 items",
					totalItems = 10
				),
				OrderModel(
					id = 6,
					purchaseDate = "2019-01-01",
					purchaserName = "Kennedy Purchaser",
					purchaserPhoto = "https://img.icons8.com/color/420/person-male.png",
					vendorName = "Kennedy Vendor",
					vendorPhoto = "https://www.dicasnutricao.com.br/wp-content/uploads/2015/06/beneficios-da-laranja.jpg",
					totalPriceLabel = "$25.90",
					totalPrice = 25.90,
					totalItemsLabel = "10 items",
					totalItems = 10
				),
				OrderModel(
					id = 7,
					purchaseDate = "2019-01-01",
					purchaserName = "Kennedy Purchaser",
					purchaserPhoto = "https://img.icons8.com/color/420/person-male.png",
					vendorName = "Kennedy Vendor",
					vendorPhoto = "https://www.dicasnutricao.com.br/wp-content/uploads/2015/06/beneficios-da-laranja.jpg",
					totalPriceLabel = "$25.90",
					totalPrice = 25.90,
					totalItemsLabel = "10 items",
					totalItems = 10
				),
				OrderModel(
					id = 8,
					purchaseDate = "2019-01-01",
					purchaserName = "Kennedy Purchaser",
					purchaserPhoto = "https://img.icons8.com/color/420/person-male.png",
					vendorName = "Kennedy Vendor",
					vendorPhoto = "https://www.dicasnutricao.com.br/wp-content/uploads/2015/06/beneficios-da-laranja.jpg",
					totalPriceLabel = "$25.90",
					totalPrice = 25.90,
					totalItemsLabel = "10 items",
					totalItems = 10
				)
			)

			return if (query != null && query.isNotEmpty()) {
				list.filter { query.toLowerCase() == it.vendorName.toLowerCase() }
			} else {
				list
			}
		}
	}
}