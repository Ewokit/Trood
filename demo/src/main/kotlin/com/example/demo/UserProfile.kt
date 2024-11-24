package com.example.demo
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
data class UserProfile(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotNull @field:Size(min = 2,max = 50)
    val name: String,

    @field:NotNull @field:Size(min = 2,max = 50)
    val surname: String,

    @field:Size(max = 100)
    val jobTitle: String ?= null,

    @field:NotNull @field:Pattern(regexp = "\\+", message = "Invalid phone number")
    val phone: String,

    @field:Size(max = 100)
    val address: String ?= null,

    @ElementCollection
    @field:Size(max = 100)
    val interests: List<@Size(max = 30) String> = emptyList(),

    val isPublic: Boolean = false,

    @field:Size(max = 100)
    val avatarUrl: String ?= null
)