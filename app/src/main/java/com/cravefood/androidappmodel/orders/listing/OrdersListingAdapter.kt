package com.cravefood.androidappmodel.orders.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cravefood.androidappmodel.R
import com.cravefood.data.OrderModel
import kotlinx.android.synthetic.main.list_item_orders_listing.view.*

class OrdersListingAdapter(
	val onClickListener: (OrderModel) -> Unit
) : RecyclerView.Adapter<OrdersListingAdapter.ViewHolder>() {

	private val ordersListing = mutableListOf<OrderModel>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item_orders_listing, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount() = ordersListing.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(ordersListing[position])
	}

	fun update(list: List<OrderModel>) {
		ordersListing.clear()
		ordersListing.addAll(list)
		notifyDataSetChanged()
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		init {
			itemView.materialCardView.setOnClickListener {
				onClickListener(ordersListing[adapterPosition])
			}
		}

		fun bind(order: OrderModel) = with(itemView) {
			Glide
				.with(context)
				.load(order.vendorPhoto)
				.centerCrop()
				.into(imageViewPhoto)


			textViewTitle.text = order.vendorName
			textViewSubtitle.text = order.totalItemsLabel
			textViewPrice.text = order.totalPriceLabel
		}
	}
}