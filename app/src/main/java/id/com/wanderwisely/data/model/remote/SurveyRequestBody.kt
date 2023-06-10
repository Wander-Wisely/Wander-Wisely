package id.com.wanderwisely.data.model.remote

import com.google.gson.annotations.SerializedName

data class SurveyRequestBody(
    @SerializedName("hobbies")
    val hobbies: List<String>,
    @SerializedName("types")
    val types: List<String>,
    @SerializedName("budget_min")
    val budgetMin: Int,
    @SerializedName("budget_max")
    val budgetMax: Int
)