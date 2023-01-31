package shouman.iss.bored.data.model

import shouman.iss.bored.data.entity.BoredDataEntity

class BoredDataResponse(
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    val key: String,
    val accessibility: Double
) : BaseResponse<BoredDataEntity> {
    override fun toEntity(): BoredDataEntity {
        return BoredDataEntity(
            activity = this.activity,
            type = this.type,
            participants = this.participants.toString(),
            price = this.price.toString(),
            link = this.link,
            key = this.key,
            accessibility = this.accessibility.toString(),
        )
    }

}