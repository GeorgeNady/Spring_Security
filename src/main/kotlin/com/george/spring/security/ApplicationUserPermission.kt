package com.george.spring.security

enum class ApplicationUserPermission private constructor(permission: String) {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");
}