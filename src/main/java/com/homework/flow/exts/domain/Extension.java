package com.homework.flow.exts.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Extension {

    private Long id;
    private String name;
    private boolean flagUse;
    private boolean flagFixed;
}

