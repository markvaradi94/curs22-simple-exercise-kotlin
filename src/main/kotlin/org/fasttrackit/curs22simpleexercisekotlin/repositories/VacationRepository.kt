package org.fasttrackit.curs22simpleexercisekotlin.repositories

import org.fasttrackit.curs22simpleexercisekotlin.domain.Vacation
import org.springframework.data.repository.CrudRepository

interface VacationRepository : CrudRepository<Vacation, Int> {
}