package com.example.shabuorderv1

import com.google.gson.annotations.SerializedName

data class ProductRes(

	@field:SerializedName("createdTime")
	val createdTime: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("fields")
	val fields: Fields? = null
)

data class Fields(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("type_id")
	val typeId: List<String?>? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	@field:SerializedName("cost_unit")
	val costUnit: Int? = null
)
