package com.example.demo

import org.springframework.data.jpa.repository.JpaRepository

interface UserProfileInterface: JpaRepository<UserProfile, Long> {
}