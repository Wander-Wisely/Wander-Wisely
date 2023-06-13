package id.com.wanderwisely.data.model.response

import com.google.gson.annotations.SerializedName

data class RecommendResponse(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class DataItem(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("descriptions")
	val descriptions: String? = null,

	@field:SerializedName("cost_to")
	val costTo: Any? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("path")
	val path: List<String?>? = null,

	@field:SerializedName("filename")
	val filename: List<String?>? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("tourism_type_id")
	val tourismTypeId: Int? = null,

	@field:SerializedName("tourism_attraction_id")
	val tourismAttractionId: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tourism_type_name")
	val tourismTypeName: String? = null,

	@field:SerializedName("cost_from")
	val costFrom: Any? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
