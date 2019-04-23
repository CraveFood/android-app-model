package com.cravefood.androidappmodel.products.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.observe
import com.cravefood.data.ProductModel
import kotlinx.android.synthetic.main.fragment_product_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetail : Fragment() {

	private val viewModel by viewModel<ProductDetailViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_product_detail, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		configureObservables()
		initScreen()
	}

	private fun configureObservables() {
		observe(viewModel.model.productModel, ::handleProductModel)
	}

	private fun initScreen() {
		viewModel.getProductByDefault(arguments?.getLong(getString(R.string.argument_product_detail_productId)))
	}

	private fun handleProductModel(product: ProductModel?) {
		Glide
			.with(this)
			.load(product?.photo ?: "")
			.centerCrop()
			.into(imageViewPhoto)

		textViewName.text = product?.name
		textViewPrice.text = product?.priceLabel
		textViewDescription.text = product?.description
	}

}
