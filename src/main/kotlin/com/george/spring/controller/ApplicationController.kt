package com.george.spring.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class ApplicationController {

    @GetMapping("/test")
    fun test() : Map<String, String> {
        return mapOf("message" to "Hello World")
    }

    @GetMapping("/students")
    fun studentRead(@RequestHeader("Authorization") token: String) : Map<String, String> {
        return mapOf("message" to "user with this basic auth `${token.substringAfter("Basic ")}` could read students")
    }

    @PostMapping("/students")
    fun studentWrite(@RequestHeader("Authorization") token: String) : Map<String, String> {
        return mapOf("message" to "user with this basic auth `${token.substringAfter("Basic ")}` could write students")
    }

    @GetMapping("/courses")
    fun courseRead(@RequestHeader("Authorization") token: String) : Map<String, String> {
        return mapOf("message" to "user with this basic auth `${token.substringAfter("Basic ")}` could read courses")
    }

    @PostMapping("/courses")
    fun courseWrite(@RequestHeader("Authorization") token: String) : Map<String, String> {
        return mapOf("message" to "user with this basic auth `${token.substringAfter("Basic ")}` could write courses")
    }

}