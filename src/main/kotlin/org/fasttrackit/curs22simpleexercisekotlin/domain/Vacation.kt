package org.fasttrackit.curs22simpleexercisekotlin.domain

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import java.io.*
import java.lang.RuntimeException
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "vacations")
data class Vacation(@Column(name = "location") val location: String,
                    @Column(name = "price") val price: Int,
                    @Column(name = "duration") val duration: Int) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
}

class VacationReader() {

    lateinit var file: Resource

    constructor(@Value("\$file.location:default.txt") fileLocation: String) : this() {
        file = ClassPathResource(fileLocation)
        if (!file.exists()) throw RuntimeException("Could not find the file in classpath $fileLocation")
    }


    fun read(): List<Vacation> {
        val result = mutableListOf<Vacation>()
        try {
            val inputStream: InputStream = file.inputStream
            val scanner = Scanner(inputStream)
            scanner.useDelimiter("[|]")
            while (scanner.hasNextLine()) {
                val tokens = scanner.nextLine().split("|")
                result.add(Vacation(
                        tokens[0],
                        tokens[1].toInt(),
                        tokens[2].toInt()
                ))
            }
            inputStream.close()
        } catch (exception: IOException) {
            System.err.println(exception.message)
        }
        return result
    }

}