package com.cravefood.androidappmodel.products.detail

import androidx.lifecycle.ViewModel
import com.cravefood.data.ProductList

class ProductDetailViewModel : ViewModel() {

	val model = ProductDetailModel()

	fun getProductByDefault(productId: Long?) {
		if (productId != null) model.productModel.value = ProductList.getProductById(productId)
	}

}