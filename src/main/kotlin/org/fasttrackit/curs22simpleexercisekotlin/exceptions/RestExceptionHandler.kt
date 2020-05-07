package org.fasttrackit.curs22simpleexercisekotlin.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.RuntimeException
import java.util.NoSuchElementException

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException): ApiError = ApiError(exception.message)
}

class ResourceNotFoundException(override val message: String) : RuntimeException()

data class ApiError(val message: String)