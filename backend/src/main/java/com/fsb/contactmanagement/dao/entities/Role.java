package com.fsb.contactmanagement.dao.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public enum Role {
    USER("User"),
    ADMIN("Admin");
    private final String value;

}
