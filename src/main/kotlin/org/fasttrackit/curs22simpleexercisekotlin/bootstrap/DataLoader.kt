package org.fasttrackit.curs22simpleexercisekotlin.bootstrap

import org.fasttrackit.curs22simpleexercisekotlin.domain.VacationReader
import org.fasttrackit.curs22simpleexercisekotlin.repositories.VacationRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(val vacationRepository: VacationRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val count = vacationRepository.count()
        if (count == 0L) loadData()
    }

    private fun loadData() {
        val fileLocation = "vacations.txt"
        val reader = VacationReader(fileLocation)
        val vacations = reader.read()
        vacations.forEach { vacation -> vacationRepository.save(vacation) }
        println("Loaded vacations ...")
    }
}