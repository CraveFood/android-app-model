package com.cravefood.androidappmodel.products.listing

import androidx.lifecycle.ViewModel
import com.cravefood.data.ProductList

class ProductsListingViewModel : ViewModel() {

	val model = ProductsListingModel()

	fun getProducts() {
		if (model.recyclerViewProductListingOb.value?.isEmpty() == true)
			model.recyclerViewProductListingOb.value = ProductList.getProductListing()
	}

}