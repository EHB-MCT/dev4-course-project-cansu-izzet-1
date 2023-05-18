package be.ehb.backend.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "session")
class Session(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,
    var date: String,

    @ManyToOne
    var user: User
)

