package pl.bartek.notesapi.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.vote.AuthenticatedVoter
import org.springframework.security.access.vote.RoleVoter
import org.springframework.security.access.vote.UnanimousBased
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.expression.WebExpressionVoter
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import pl.bartek.notesapi.user.UserService

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(
    val userService: UserService,
    val unauthorizedHandler: AuthenticationEntryPoint,
    val succsesHandler: WebSecurityAuthSuccessHandler
) : WebSecurityConfigurerAdapter() {

    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider())
    }

    override fun configure(http: HttpSecurity?) {
        http
            ?.csrf()?.disable()
            ?.exceptionHandling()
            ?.authenticationEntryPoint(unauthorizedHandler)
            ?.and()
            ?.authorizeRequests()
            ?.antMatchers("/login")?.permitAll()
            ?.antMatchers("/notes")?.authenticated()
            ?.antMatchers("/notes/**")?.authenticated()
            ?.antMatchers("/todos")?.authenticated()
            ?.antMatchers("/todos/**")?.authenticated()
            ?.antMatchers("/users")?.hasAnyAuthority("ADMIN")
            ?.antMatchers("/users/**")?.hasAnyAuthority("ADMIN")
            ?.and()
            ?.formLogin()
            ?.successHandler(succsesHandler)
            ?.failureHandler(SimpleUrlAuthenticationFailureHandler())
            ?.and()
            ?.logout()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder(11)

    @Bean
    fun accessDecisionManager(): AccessDecisionManager {
        val decisionVoters = listOf(WebExpressionVoter(), RoleVoter(), AuthenticatedVoter())
        return UnanimousBased(decisionVoters)
    }
}