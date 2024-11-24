package com.example.demo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "file")
class FileStorage {
    lateinit var uploadDirectories: String
}