package be.ehb.backend.config

import be.ehb.backend.dto.CreateUserRequest
import be.ehb.backend.repositories.UserRepository
import be.ehb.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class AdminUserConfig : CommandLineRunner {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userService: UserService

    @Value("\${admin.username}")
    private lateinit var adminUsername: String

    @Value("\${admin.password}")
    private lateinit var adminPassword: String

    @Value("\${admin.email}")
    private lateinit var adminEmail: String

    override fun run(vararg args: String?) {
        if (userRepository.findByUsername("admin") == null) {
            val admin = CreateUserRequest(username = adminUsername, password = adminPassword, email = adminEmail)
            userService.saveUser(admin, "Admin")
        }
    }
}
