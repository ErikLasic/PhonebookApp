package com.example.lib

import io.github.serpro69.kfaker.Faker

fun generate(iterator: Int): Phonebook {
    val faker = Faker()
    val imenik = Phonebook("Ptujski imenik")
    for (i in 1..iterator) {
        imenik.add(PhoneUserContact(faker.phoneNumber.phoneNumber(), faker.name.name(),
                PhoneAddress(faker.address.city(), (1000..3000).random(), faker.address.buildingNumber())
            )
        )
    }
    return imenik
}

fun main() {
    val imenik = generate(10)
    imenik.sort()
    println(imenik.toString())
}