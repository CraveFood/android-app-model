package com.cravefood.androidappmodel

import com.cravefood.androidappmodel.products.detail.ProductDetailViewModel
import com.cravefood.androidappmodel.products.listing.ProductsListingViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val ViewModelModules: Module = module {
	viewModel { ProductsListingViewModel() }
	viewModel { ProductDetailViewModel() }
}