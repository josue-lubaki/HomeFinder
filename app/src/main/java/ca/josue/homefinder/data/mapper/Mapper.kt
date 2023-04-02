package ca.josue.homefinder.data.mapper

import ca.josue.homefinder.data.models.house.DataHouse
import ca.josue.homefinder.domain.models.House

/**
 * created by Josue Lubaki
 * date : 2023-04-02
 * version : 1.0.0
 */

fun DataHouse.toDomain() : House {
    return House(
        id = id,
        uuid = uuid,
        description = description,
        images = images,
        price = price,
        address = address,
        bedrooms = bedrooms,
        bathrooms = bathrooms,
        area = area,
        type = type,
        yearBuilt = yearBuilt,
        pool = pool,
        owner = owner
    )
}

fun List<DataHouse>.toDomain() : List<House> {
    return map { it.toDomain() }
}