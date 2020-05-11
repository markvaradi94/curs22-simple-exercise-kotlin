package org.fasttrackit.curs22simpleexercisekotlin.services

import org.fasttrackit.curs22simpleexercisekotlin.domain.Vacation
import org.fasttrackit.curs22simpleexercisekotlin.exceptions.ResourceNotFoundException
import org.fasttrackit.curs22simpleexercisekotlin.repositories.VacationRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors.toList
import java.util.stream.StreamSupport

@Service
class VacationService(val vacationRepository: VacationRepository) {

    fun getAllVacations() = vacationRepository.findAll().toList()

    fun getVacationById(id: Int) = getOrThrow(id)

    fun vacationsInLocation(location: String): List<Vacation> = StreamSupport
            .stream(vacationRepository.findAll().spliterator(), false)
            .filter { it.location.equals(location, ignoreCase = true) }
            .collect(toList())

    fun vacationsWithPriceLowerThan(maxPrice: Int): List<Vacation> = StreamSupport
            .stream(vacationRepository.findAll().spliterator(), false)
            .filter { it.price <= maxPrice }
            .collect(toList())

    fun add(vacation: Vacation) = vacationRepository.save(vacation)

    fun delete(id: Int): Vacation {
        val vacationToDelete = getOrThrow(id)
        vacationRepository.delete(vacationToDelete)
        return vacationToDelete
    }

    fun replace(id: Int, vacation: Vacation): Vacation {
        val vacationToReplace = getOrThrow(id)
        vacationRepository.delete(vacationToReplace)
        vacationRepository.save(vacation)
        return vacation
    }

    fun getOrThrow(id: Int): Vacation = vacationRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("Could not find vacation with id $id") }
}