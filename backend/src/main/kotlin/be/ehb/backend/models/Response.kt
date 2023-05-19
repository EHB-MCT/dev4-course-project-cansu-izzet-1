package be.ehb.backend.models

import jakarta.persistence.*

@Entity
@Table(name = "response")
class Response (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @OneToOne
    @JoinColumn(name = "questionnaire_id")
    var questionnaire: Questionnaire? = null,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "response_id")
    var answers: List<Answer> = ArrayList()
//    var answers: Set<Answer> = HashSet()

){
}