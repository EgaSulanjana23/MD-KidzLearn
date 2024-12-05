package com.c242ps187.kidzlearnapp.data.response

import com.google.gson.annotations.SerializedName

data class LearningAnimalsResponse(

	@field:SerializedName("animals_data")
	val animalsData: List<AnimalsDataItem>,

	@field:SerializedName("message")
	val message: String
)

data class AnimalsDataItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("urlImages")
	val urlImages: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("urlSuaraHewan")
	val urlSuaraHewan: String
)
