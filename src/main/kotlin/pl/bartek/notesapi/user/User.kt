package pl.bartek.notesapi.user

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "User")
@JsonInclude(JsonInclude.Include.NON_NULL)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "userType")
open class User(
    @Id
    @Email
    open var email: String = "",
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    open var pwd: String = "",
    @NotBlank
    open var firstName: String = "",
    @NotBlank
    open var lastName: String = "",
    open var roles: String = "",
    open var enabled: Boolean = true,
    open var accountNonExpired: Boolean = true,
    open var accountNonLocked: Boolean = true,
    open var credentialsNonExpired: Boolean = true,
    @CreationTimestamp
    open var created: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    open var modified: LocalDateTime = LocalDateTime.now()
) : UserDetails {
    constructor() : this("")

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.split(",").map { SimpleGrantedAuthority(it.trim()) }.toCollection(mutableListOf())
    }

    override fun isEnabled() = enabled

    override fun getUsername() = email

    override fun getPassword() = pwd

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked
}