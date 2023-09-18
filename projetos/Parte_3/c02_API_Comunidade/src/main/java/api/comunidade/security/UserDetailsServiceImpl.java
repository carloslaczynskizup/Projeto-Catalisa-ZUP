package api.comunidade.security;

import api.comunidade.roles.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT * username, password, roles FROM usuarios WHERE username = ?";

        Object[] params= {username};
        Map<String, Object> userMap = jdbcTemplate.queryForMap(sql, params);

        if(userMap == null){
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        String password = (String) userMap.get("password");
        String roles = (String) userMap.get("roles");

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsername(username);
        usuarioModel.setPassword(password);
        usuarioModel.setRoles(roles);

        return new User(usuarioModel.getUsername(), usuarioModel.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(usuarioModel.getRoles())));
    }
}
