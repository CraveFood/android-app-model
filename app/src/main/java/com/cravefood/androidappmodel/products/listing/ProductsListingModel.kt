package com.cravefood.androidappmodel.products.listing

import androidx.lifecycle.MutableLiveData
import com.cravefood.data.ProductModel

class ProductsListingModel {
	val recyclerViewProductListingOb =
		MutableLiveData<List<ProductModel>>().apply { value = emptyList() }
}