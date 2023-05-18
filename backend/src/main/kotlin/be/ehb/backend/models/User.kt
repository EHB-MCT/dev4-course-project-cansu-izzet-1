package be.ehb.backend.models

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,

    @Column(unique = true)
    var username: String,

    @Column(unique = true)
    var email: String,
    var password: String,
    var role: String

) {
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var sessions: MutableList<Session> = mutableListOf()

    var accessToken = ""
    var expirationDate = System.currentTimeMillis()
}