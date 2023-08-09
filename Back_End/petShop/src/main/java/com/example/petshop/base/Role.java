package com.example.petshop.base;

//@RequiredArgsConstructor
public enum Role {
    USER,
    FUNC,
    ADMIN
    /*CLIENTE(Collections.emptySet()),
    FUNCIONARIO(
            Set.of(
                    FUN_READ,
                    FUN_UPDATE,
                    FUN_CREATE,
                    FUN_DELETE
            )
    ),
    ADMINISTRADOR(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    FUN_READ,
                    FUN_UPDATE,
                    FUN_CREATE,
                    FUN_DELETE
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
    }*/
}
