package com.varchar6.petcast.domain.member.query.vo;

import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO {
    private String name;
    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleVO roleVO = (RoleVO) o;
        return active == roleVO.active &&
                Objects.equals(name, roleVO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, active);
    }
}
