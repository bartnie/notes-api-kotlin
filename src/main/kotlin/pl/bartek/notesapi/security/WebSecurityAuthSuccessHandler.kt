package pl.bartek.notesapi.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class WebSecurityAuthSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {
    var requestCache = HttpSessionRequestCache()

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        if (requestCache.getRequest(request, response) == null) {
            clearAuthenticationAttributes(request)
            return
        }
        val parameter = request?.getParameter(targetUrlParameter)
        if (isAlwaysUseDefaultTargetUrl || targetUrlParameter != null && StringUtils.hasText(parameter)) {
            requestCache.removeRequest(request, response)
        }
        clearAuthenticationAttributes(request)
    }
}