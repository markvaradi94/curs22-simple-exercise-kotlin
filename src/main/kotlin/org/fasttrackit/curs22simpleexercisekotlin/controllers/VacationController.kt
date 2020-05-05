package org.fasttrackit.curs22simpleexercisekotlin.controllers

import org.fasttrackit.curs22simpleexercisekotlin.domain.Vacation
import org.fasttrackit.curs22simpleexercisekotlin.services.VacationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("vacations")
class VacationController(val vacationService: VacationService) {

    @GetMapping
    fun getAll(@RequestParam(required = false) maxPrice: Int?): List<Vacation> {
        return if (maxPrice == null) vacationService.getAllVacations()
        else vacationService.vacationsWithPriceLowerThan(maxPrice)
    }

    @GetMapping("{id}")
    fun getVacationById(@PathVariable(required = false) id: Int) = vacationService.getVacationById(id)

    @GetMapping("/location/{location}")
    fun vacationsInLocation(@PathVariable(required = false) location: String) =
            vacationService.vacationsInLocation(location)

    @PostMapping
    fun addVacation(@RequestBody vacation: Vacation) = vacationService.add(vacation)

    @PutMapping("{id}")
    fun replaceVacation(@PathVariable(required = false) id: Int, @RequestBody vacation: Vacation) =
            vacationService.replace(id, vacation)

    @DeleteMapping("{id}")
    fun deleteVacation(@PathVariable(required = false) id: Int) = vacationService.delete(id)
}