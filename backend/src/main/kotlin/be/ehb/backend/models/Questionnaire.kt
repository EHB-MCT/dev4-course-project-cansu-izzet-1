package be.ehb.backend.models

import jakarta.persistence.*

@Entity
@Table(name = "questionnaire")
class Questionnaire (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @OneToOne
    @JoinColumn(name = "session_id")
    var session: Session? = null,

    @ManyToMany
    @JoinTable(
        name = "questionnaire_question",
        joinColumns = [JoinColumn(name = "questionnaire_id")],
        inverseJoinColumns = [JoinColumn(name = "question_id")]
    )
    var questions: Set<Question> = HashSet()
){
}