package ca.josue.homefinder.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "owners_table")
data class Owner(
    @PrimaryKey
    val id: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
)
