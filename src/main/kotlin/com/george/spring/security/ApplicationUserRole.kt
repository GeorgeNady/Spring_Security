package com.george.spring.security

enum class ApplicationUserRole(permissions: Set<ApplicationUserPermission>) {
    ADMIN(hashSetOf(/*COURSE_WRITE, COURSE_READ, STUDENT_READ, STUDENT_WRITE*/)),
    STUDENT(hashSetOf(/*STUDENT_READ, STUDENT_WRITE, COURSE_READ*/));
}
