package com.cravefood.androidappmodel.products.listing

import androidx.lifecycle.ViewModel
import com.cravefood.data.ProductList

class ProductsListingViewModel : ViewModel() {

	val model = ProductsListingModel()

	fun getProducts(query: String? = null) {
		model.recyclerViewProductListingOb.value = ProductList.getProductListing(query)
	}

}