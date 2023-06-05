package id.com.wanderwisely.data.model.response

import com.google.gson.annotations.SerializedName

data class WisataResponse(

	@field:SerializedName("provinsi")
	val provinsi: String? = null,

	@field:SerializedName("sampai_biaya")
	val sampaiBiaya: String? = null,

	@field:SerializedName("jenis_wisata")
	val jenisWisata: String? = null,

	@field:SerializedName("kota")
	val kota: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("Media")
	val media: List<MediaItem?>? = null,

	@field:SerializedName("nama_tempat")
	val namaTempat: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("dari_biaya")
	val dariBiaya: String? = null,

	@field:SerializedName("Aktivitas")
	val aktivitas: List<AktivitasItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("Fasilitas")
	val fasilitas: List<FasilitasItem?>? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class FasilitasItem(

	@field:SerializedName("id_tempat")
	val idTempat: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class MediaItem(

	@field:SerializedName("id_tempat")
	val idTempat: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class AktivitasItem(

	@field:SerializedName("id_tempat")
	val idTempat: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
