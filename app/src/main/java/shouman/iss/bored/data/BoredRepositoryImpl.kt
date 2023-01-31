package shouman.iss.bored.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import shouman.iss.bored.api.BoredApi
import shouman.iss.bored.api.BoredDataApi
import shouman.iss.bored.data.entity.BoredDataEntity
import shouman.iss.bored.domain.BoredRepository

class BoredRepositoryImpl : BoredRepository {
    val boredApi: BoredDataApi = BoredApi.getInstance().create(BoredDataApi::class.java)
    val unknown: String = "N/A"

    override suspend fun getBoredData(): BoredDataEntity {
       return withContext(Dispatchers.IO){
            val result = boredApi.getActivity()
            if(result.body() != null){
                return@withContext result.body()!!.toEntity()
            }
           return@withContext BoredDataEntity.EmptyDataEntity(emptyValueRepresentative = unknown)

        }

    }
}