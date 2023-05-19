package be.ehb.backend.models

import jakarta.persistence.*

@Entity
@Table(name = "answer")
class Answer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var questionName: String,
    var questionScore: Int,

    @ManyToOne
    @JoinColumn(name = "response_id")
    var response: Response? = null
)