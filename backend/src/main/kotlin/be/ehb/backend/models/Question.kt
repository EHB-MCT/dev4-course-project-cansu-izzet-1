package be.ehb.backend.models

import jakarta.persistence.*

@Entity
@Table(name = "question")
class Question (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var description: String,
) {
}