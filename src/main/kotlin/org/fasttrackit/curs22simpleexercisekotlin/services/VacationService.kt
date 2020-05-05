package org.fasttrackit.curs22simpleexercisekotlin.services

import org.fasttrackit.curs22simpleexercisekotlin.domain.Vacation
import org.fasttrackit.curs22simpleexercisekotlin.exceptions.ResourceNotFoundException
import org.fasttrackit.curs22simpleexercisekotlin.repositories.VacationRepository
import org.springframework.stereotype.Service

@Service
class VacationService(val vacationRepository: VacationRepository) {

    fun getAllVacations() = vacationRepository.findAll().toList()

    fun getVacationById(id: Int) = getOrThrow(id)

    fun vacationsInLocation(location: String) = getAllVacations()
            .filter { it.location.equals(location, ignoreCase = true) }

    fun vacationsWithPriceLowerThan(maxPrice: Int) = getAllVacations().filter { it.price <= maxPrice }

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