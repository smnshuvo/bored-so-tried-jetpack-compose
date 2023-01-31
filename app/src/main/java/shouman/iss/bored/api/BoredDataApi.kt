package shouman.iss.bored.api

import retrofit2.Response
import retrofit2.http.GET
import shouman.iss.bored.data.model.BoredDataResponse

interface BoredDataApi {
    @GET(Endpoints.activity)
    suspend fun getActivity() : Response<BoredDataResponse>
}