package shouman.iss.bored.data.model

interface BaseResponse<T>{
    fun toEntity() : T
}