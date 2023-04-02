package ca.josue.homefinder.data.local.db

import androidx.room.TypeConverter
import ca.josue.homefinder.domain.models.Address
import ca.josue.homefinder.domain.models.Owner

/**
 * created by Josue Lubaki
 * date : 2023-03-26
 * version : 1.0.0
 */

class DatabaseConverter {

    private val separator = ";"

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val sb = StringBuilder()
        for (item in list) {
            sb.append(item).append(separator)
        }
        return sb.toString()
    }

    @TypeConverter
    fun convertStringToList(str: String): List<String> {
        return str.split(separator)
    }

    @TypeConverter
    fun convertAddressToString(address: Address): String {
        val sb = StringBuilder()
        sb.append(address.id).append(separator)
        sb.append(address.number).append(separator)
        sb.append(address.street).append(separator)
        sb.append(address.city).append(separator)
        sb.append(address.province).append(separator)
        sb.append(address.postalCode).append(separator)
        sb.append(address.country).append(separator)

        return sb.toString()
    }

    @TypeConverter
    fun convertStringToAddress(value: String): Address {
        val list = value.split(separator)
        return Address(
            id = list[0],
            number = list[1],
            street = list[2],
            city = list[3],
            province = list[4],
            postalCode = list[5],
            country = list[6]
        )
    }

    @TypeConverter
    fun convertOwnerToString(owner: Owner): String {
        val sb = StringBuilder()
        sb.append(owner.id).append(separator)
        sb.append(owner.username).append(separator)
        sb.append(owner.firstName).append(separator)
        sb.append(owner.lastName).append(separator)
        sb.append(owner.email).append(separator)
        sb.append(owner.phone).append(separator)

        return sb.toString()
    }

    @TypeConverter
    fun convertStringToOwner(value: String): Owner {
        val list = value.split(separator)
        return Owner(
            id = list[0],
            username = list[1],
            firstName = list[2],
            lastName = list[3],
            email = list[4],
            phone = list[5]
        )
    }
}