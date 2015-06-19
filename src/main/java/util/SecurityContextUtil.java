package util;

import model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * Created on 31.08.2014.
 */
public class SecurityContextUtil {

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isNoAuth(authentication) ? null : (User) authentication.getPrincipal();
    }


    public static Collection<? extends GrantedAuthority> getCurrentUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isNoAuth(authentication) ? null : authentication.getAuthorities();
    }

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isNoAuth(authentication) ? null : ((User) authentication.getPrincipal()).getId();
    }

    private static boolean isNoAuth(Authentication authentication) {
        return authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken);
    }

    public static Authentication setAuthentication(User user) {
        Authentication authToken =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null
                );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        return authToken;
    }


    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !isNoAuth(authentication);
    }
}
