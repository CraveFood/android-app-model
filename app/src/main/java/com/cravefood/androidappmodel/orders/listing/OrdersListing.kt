package com.cravefood.androidappmodel.orders.listing

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cravefood.androidappmodel.R
import com.cravefood.androidappmodel.observe
import com.cravefood.data.OrderModel
import com.example.common.DebounceQueryTextListener
import kotlinx.android.synthetic.main.fragment_orders_listing.*
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrdersListing : Fragment() {

	private val viewModel by viewModel<OrdersListingViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_orders_listing, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setHasOptionsMenu(true)

		configureObservables()
		configureComponents()
		getData()
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		inflater?.inflate(R.menu.menu_search, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onPrepareOptionsMenu(menu: Menu?) {
		menu?.let {
			val searchMenuItem: MenuItem? = menu.findItem(R.id.action_search_view)
			val searchView = (searchMenuItem?.actionView as SearchView)
			searchView.queryHint = getString(R.string.search_orders)
			searchView.maxWidth = Integer.MAX_VALUE
			searchView.setOnQueryTextListener(object : DebounceQueryTextListener(context) {

				override fun doOnTextChanged(text: String) {
					if (text.isEmpty() || text.length > 2)
						viewModel.getOrders(text)
				}

				override fun onQueryTextSubmit(query: String): Boolean {
					doOnTextChanged(query)
					return false
				}
			})
		}
	}

	private fun configureObservables() {
		observe(viewModel.model.recyclerViewOrdersListingOb, ::handleOrdersListing)
	}

	private fun configureComponents() {
		recyclerViewOrdersListing.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = OrdersListingAdapter { order ->
				toast(order.vendorName)
			}
		}
	}

	private fun getData() {
		viewModel.getOrders()
	}

	private fun handleOrdersListing(list: List<OrderModel>?) {
		(recyclerViewOrdersListing.adapter as OrdersListingAdapter).update(list ?: emptyList())
	}

}
