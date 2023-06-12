package id.com.wanderwisely.ui.detail.frament

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.com.wanderwisely.data.model.remote.ApiConfig
import id.com.wanderwisely.data.model.response.WisataResponse
import id.com.wanderwisely.data.repository.Result
import id.com.wanderwisely.data.repository.WanderWiselyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DescriptionFragmenViewModel: ViewModel() {

    var detailDesc = MutableLiveData<WisataResponse>()
    fun getDetailWisata(id: Int?) {
        val client = ApiConfig.getWisataApiService().getDetailWisata(id)
        client.enqueue(object : Callback<WisataResponse> {
            override fun onResponse(
                call: Call<WisataResponse>,
                response: Response<WisataResponse>,
            ) {
                if (response.isSuccessful) {
                    val detailDescription = response.body()
                    if (detailDescription != null) {
                        detailDesc.postValue(response.body())
                    } else {
                        Log.e("Cek salah", "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<WisataResponse>, t: Throwable) {
                Log.e("Cek salah", "onFailure: ${t.message}")
            }
        })

    }

    fun getWisataDetail(): LiveData<WisataResponse> {
        return detailDesc
    }

}