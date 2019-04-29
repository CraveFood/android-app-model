package com.cravefood.androidappmodel.products.listing

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.observe
import com.cravefood.data.ProductModel
import com.example.common.DebounceQueryTextListener
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
		setHasOptionsMenu(true)

		configureObservable()
		configureComponents()
		getData()
	}

	private fun configureObservable() {
		observe(viewModel.model.recyclerViewProductListingOb, ::handleProductListing)
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		inflater?.inflate(R.menu.menu_search, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onPrepareOptionsMenu(menu: Menu?) {
		menu?.let {
			val searchMenuItem: MenuItem? = menu.findItem(R.id.action_search_view)
			val searchView = (searchMenuItem?.actionView as SearchView)
			searchView.queryHint = getString(R.string.search_products)
			searchView.maxWidth = Integer.MAX_VALUE
			searchView.setOnQueryTextListener(object : DebounceQueryTextListener(context) {

				override fun doOnTextChanged(text: String) {
					if (text.isEmpty() || text.length > 2)
						viewModel.getProducts(text)
				}

				override fun onQueryTextSubmit(query: String): Boolean {
					doOnTextChanged(query)
					return false
				}
			})
		}
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
