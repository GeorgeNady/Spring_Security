package com.george.spring.model

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID,
    var name: String,
    @Column(unique = true)
    var email: String,
    @OneToMany(fetch = FetchType.EAGER)
    var authorities: MutableList<Role>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , email = $email , authorities = $authorities )"
    }
}