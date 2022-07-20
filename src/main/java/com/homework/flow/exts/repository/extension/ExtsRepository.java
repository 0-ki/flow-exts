package com.homework.flow.exts.repository.extension;


import com.homework.flow.exts.domain.Extension;

import java.util.List;
import java.util.Optional;

public interface ExtsRepository {
    Extension save(com.homework.flow.exts.domain.Extension extension);

    int update(Extension extensionDTO);

    Optional<Extension> findById(Long id);

    Optional<Extension> findByName(String name);

    List<Extension> findAll();

    int delete(Long id);

    int getCountCustom();
}
