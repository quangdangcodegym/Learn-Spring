package com.codegym.model;

import lombok.Getter;
import lombok.Setter;

public enum EColor {
    BLACK(1L, "Màu Đen"), BLUE (2L,"Màu xanh"), RED (3L, "Màu đỏ");
    private Long id;
    private String name;

    private EColor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
