package com.cravefood.androidappmodel.products.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.observe
import com.cravefood.data.ProductModel
import kotlinx.android.synthetic.main.fragment_products_listing.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsListing : Fragment() {

	private val viewModel by viewModel<ProductsListingViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_products_listing, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		configureObservable()
		configureComponents()
		getData()
	}

	private fun configureObservable() {
		observe(viewModel.model.recyclerViewProductListingOb, ::handleProductListing)
	}

	private fun configureComponents() {
		recyclerViewProductsListing.apply {
			layoutManager = LinearLayoutManager(context)
			adapter =
				ProductsListingAdapter { product ->
					val bundle =
						bundleOf(
							getString(R.string.argument_product_detail_productId) to product.id
						)

					findNavController().navigate(
						R.id.action_productsListing_to_productDetail,
						bundle
					)
				}
		}
	}

	private fun getData() {
		viewModel.getProducts()
	}

	private fun handleProductListing(list: List<ProductModel>?) {
		(recyclerViewProductsListing.adapter as ProductsListingAdapter).update(list ?: emptyList())
	}
}
