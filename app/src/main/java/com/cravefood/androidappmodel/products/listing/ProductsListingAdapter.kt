package com.cravefood.androidappmodel.products.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cravefood.androidappmodel.R
import com.cravefood.data.ProductModel
import kotlinx.android.synthetic.main.list_item_products_listing.view.*

class ProductsListingAdapter(
	val onClickListener: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductsListingAdapter.ViewHolder>() {

	private val productsListing = mutableListOf<ProductModel>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.list_item_products_listing, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount() = productsListing.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(productsListing[position])
	}

	fun update(list: List<ProductModel>) {
		productsListing.clear()
		productsListing.addAll(list)
		notifyDataSetChanged()
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		init {
			itemView.materialCardView.setOnClickListener {
				onClickListener(productsListing[adapterPosition])
			}
		}

		fun bind(product: ProductModel) = with(itemView) {
			Glide
				.with(context)
				.load(product.photo)
				.centerCrop()
				.into(imageViewPhoto)

			textViewTitle.text = product.name
			textViewSubtitle.text = product.description
			textViewPrice.text = product.priceLabel
			textViewUnit.text = product.unit
		}
	}
}