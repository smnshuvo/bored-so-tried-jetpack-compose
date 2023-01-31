package shouman.iss.bored.data.entity

open class BoredDataEntity(
    val activity: String,
    val type: String,
    val participants: String,
    val price: String,
    val link: String,
    val key: String,
    val accessibility: String
){
    class EmptyDataEntity(emptyValueRepresentative: String) : BoredDataEntity(
            emptyValueRepresentative,
            emptyValueRepresentative,
            emptyValueRepresentative,
            emptyValueRepresentative,
            emptyValueRepresentative,
            emptyValueRepresentative,
            emptyValueRepresentative
        )


}