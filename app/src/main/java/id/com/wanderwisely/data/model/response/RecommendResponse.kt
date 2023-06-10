package id.com.wanderwisely.data.model.response

import com.google.gson.annotations.SerializedName

data class RecommendResponse(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataItem(

	@field:SerializedName("tourism_types.createdAt")
	val tourismTypesCreatedAt: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("descriptions")
	val descriptions: String? = null,

	@field:SerializedName("cost_to")
	val costTo: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("tourism_type_id")
	val tourismTypeId: Int? = null,

	@field:SerializedName("tourism_types.id")
	val tourismTypesId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tourism_types.name")
	val tourismTypesName: String? = null,

	@field:SerializedName("tourism_types.updatedAt")
	val tourismTypesUpdatedAt: String? = null,

	@field:SerializedName("cost_from")
	val costFrom: Int? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
