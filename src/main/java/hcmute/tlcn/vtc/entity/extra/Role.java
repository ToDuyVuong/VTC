package hcmute.tlcn.vtc.entity.extra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static hcmute.tlcn.vtc.entity.extra.Permission.*;

@RequiredArgsConstructor
public enum Role {

//        CUSTOMER(
//                Set.of(
//                        CUSTOMER_READ,
//                        CUSTOMER_UPDATE,
//                        CUSTOMER_DELETE,
//                        CUSTOMER_CREATE
//                )
//        ),
    CUSTOMER(Collections.emptySet()),
    VENDOR(
            Set.of(
                    VENDOR_READ,
                    VENDOR_UPDATE,
                    VENDOR_DELETE,
                    VENDOR_CREATE
            )
    ),

    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    );

    @Getter
    private final Set<Permission> permissions;


    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
