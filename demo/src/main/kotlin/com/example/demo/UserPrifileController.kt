package com.example.demo

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("/profile")
@Validated
class UserProfileController(private val repository: UserProfileInterface) {
    fun getProfile(): UserProfile{
        return repository.findById(1).orElseThrow{
            Exception("Profile not found")
        }
    }
    @PutMapping
    fun updateProfile(@RequestBody @Validated profile: UserProfile):UserProfile{
        val existingProfile = repository.findById(1).orElseThrow{
            Exception("Not Found") }
        val updateProfile = existingProfile.copy(
            name = profile.name,
            surname = profile.surname,
            jobTitle = profile.jobTitle,
            phone = profile.phone,
            address = profile.address,
            interests = profile.interests,
            isPublic = profile.isPublic
        )
        return repository.save(updateProfile)
    }

    @PostMapping("/upload-avatar")
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadAvatar(@RequestParam("file") file: MultipartFile):Map<String, String>{
        val uploadDirectories = Paths.get("uploads")
        if (!Files.exists(uploadDirectories)){
            Files.createDirectories(uploadDirectories)
        }
        val filePath = uploadDirectories.resolve(file.originalFilename!!)
        file.inputStream.use {
            Files.copy(it, filePath)
        }
        val fileUrl = "/uploads/${file.originalFilename}"
        return mapOf("url" to fileUrl)
    }
}