package con.rolfie.dealabs.model.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AppUser extends User {

    public static AppUser create(final String username,
                                 final String password,
                                 final Collection<? extends GrantedAuthority> authorities) {
        return new AppUser(username, password, authorities);
    }

    private AppUser(final String username,
                    final String password,
                    final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
