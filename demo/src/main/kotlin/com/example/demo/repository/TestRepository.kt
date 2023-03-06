package com.example.demo.repository

import org.jdbi.v3.core.Handle
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Repository

@Repository
class TestRepository (
    @Lazy private val handle: Handle
) {
    fun test() {

    }
}
