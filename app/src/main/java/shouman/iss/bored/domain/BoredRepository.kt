package shouman.iss.bored.domain

import shouman.iss.bored.data.entity.BoredDataEntity

interface BoredRepository {
    suspend fun getBoredData() : BoredDataEntity
}