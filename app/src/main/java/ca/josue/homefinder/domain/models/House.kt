package ca.josue.homefinder.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "houses_table")
data class House(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val uuid: Int,
    val description: String,
    val images: List<String>,
    val price: Long,
    val address: Address,
    val bedrooms: Int,
    val bathrooms: Int,
    val area: Int,
    val type: String,
    val yearBuilt: Int,
    val pool: Boolean,
    val owner: Owner,
    val isLiked: Boolean = false,
)