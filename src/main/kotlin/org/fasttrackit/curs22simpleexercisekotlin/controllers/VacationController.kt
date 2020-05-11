package org.fasttrackit.curs22simpleexercisekotlin.controllers

import org.fasttrackit.curs22simpleexercisekotlin.domain.Vacation
import org.fasttrackit.curs22simpleexercisekotlin.services.VacationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("vacations")
class VacationController(val vacationService: VacationService) {

    @GetMapping
    fun getAll(@RequestParam(required = false) maxPrice: Int?,
               @RequestParam(required = false) location: String?): List<Vacation> {
        return if (maxPrice == null && location == null) vacationService.getAllVacations()
        else if (maxPrice != null && location == null) vacationService.vacationsWithPriceLowerThan(maxPrice)
        else vacationService.vacationsInLocation(location.toString())
    }

    @GetMapping("{id}")
    fun getVacationById(@PathVariable(required = false) id: Int) = vacationService.getVacationById(id)

    @PostMapping
    fun addVacation(@RequestBody vacation: Vacation) = vacationService.add(vacation)

    @PutMapping("{id}")
    fun replaceVacation(@PathVariable(required = false) id: Int, @RequestBody vacation: Vacation) =
            vacationService.replace(id, vacation)

    @DeleteMapping("{id}")
    fun deleteVacation(@PathVariable(required = false) id: Int) = vacationService.delete(id)
}