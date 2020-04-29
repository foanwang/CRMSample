package com.foan.crm.constants;

import lombok.Data;

public enum RoleType {
    OPERATOR(0),
    MANAGER(1),
    SUPERUSER(2);
    /**
     * An integer or 0 to identify the email platform.
     */
    private int id;

    RoleType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
