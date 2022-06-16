package com.george.spring.model

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?,
    @Column(unique = true)
    var username: String,
    var password: String,
    @OneToMany(fetch = FetchType.EAGER)
    var authorities: MutableSet<Role> = hashSetOf()
)