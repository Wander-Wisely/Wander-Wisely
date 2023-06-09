package id.com.wanderwisely.data.model.response

import com.google.gson.annotations.SerializedName

data class WisataResponse(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("TourismType")
	val tourismType: TourismType? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("TourismActivities")
	val tourismActivities: List<TourismActivitiesItem?>? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("descriptions")
	val descriptions: String? = null,

	@field:SerializedName("TourismFacilities")
	val tourismFacilities: List<TourismFacilitiesItem?>? = null,

	@field:SerializedName("cost_to")
	val costTo: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("tourism_type_id")
	val tourismTypeId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("TourismFiles")
	val tourismFiles: List<TourismFilesItem?>? = null,

	@field:SerializedName("cost_from")
	val costFrom: Int? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class TourismFilesItem(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("filename")
	val filename: String? = null,

	@field:SerializedName("tourism_attraction_id")
	val tourismAttractionId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class TourismFacilitiesItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("tourism_attraction_id")
	val tourismAttractionId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class TourismType(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class TourismActivitiesItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("tourism_attraction_id")
	val tourismAttractionId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
