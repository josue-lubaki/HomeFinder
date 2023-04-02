package ca.josue.homefinder.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "address_table")
data class Address(
    @PrimaryKey
    val id : String,
    val number : String,
    val street : String,
    val city : String,
    val province : String,
    val postalCode : String,
    val country : String,
)
