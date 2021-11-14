package con.rolfie.dealabs.security.provider;

import con.rolfie.dealabs.model.database.dao.UserRepository;
import con.rolfie.dealabs.model.database.entity.UserDo;
import con.rolfie.dealabs.model.security.AppUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    public static UserAuthenticationProvider create(final UserRepository userRepository) {
        return new UserAuthenticationProvider(userRepository);
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final var name = authentication.getName();
        final var credentials = (String) authentication.getCredentials();

        userRepository.findByFirstName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user " + name));

        return new UsernamePasswordAuthenticationToken(
                AppUser.create(name, credentials, getAuthorities()),
                credentials,
                getAuthorities()
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean supports(final Class<?> authenticate) {
        return authenticate.equals(UsernamePasswordAuthenticationToken.class);
    }
}
