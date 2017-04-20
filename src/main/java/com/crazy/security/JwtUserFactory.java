package com.crazy.security;

import com.crazy.entity.Account;
import com.crazy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yuhao on 2017/4/19.
 */
public class JwtUserFactory {
    @Autowired
    static RoleRepository roleRepository;

    private JwtUserFactory() {
    }

    public static JwtUser create(Account account) {
        return new JwtUser(
                account.getAccount_id(),
                account.getUsername(),
                account.getPassword(),
                account.getExt_params(),
                account.getName(),
                account.getIcon(),
                account.getEmail(),
                account.getCreate_time(),
                account.getUpdate_time(),
                account.getMobile(),
                account.getDev_id(),
                mapToGrantedAuthorities(account.getRole_id())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Long role_id) {
        //此处repository调用存在一定问题，先用role_id手动判定代替而不访问数据库。
        //List<Role> roles = roleRepository.findByRoleId(role_id);
        List<String> role_name = new ArrayList();
//        for (int i = 0; i < roles.size(); i++) {
//            role_name.add(roles.get(i).getRoleName());
//        }
        if (role_id == 1)
            role_name.add("user");
        else if (role_id == 2)
            role_name.add("admin");
        return role_name.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
