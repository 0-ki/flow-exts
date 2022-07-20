package com.homework.flow.exts.service.extension;

import com.homework.flow.exts.domain.Extension;

import java.util.List;
import java.util.Optional;

public interface ExtsService {

    Extension saveCustom(com.homework.flow.exts.domain.Extension extension);

    int update(Extension extension);

    Optional<Extension> findById(Long id);

    Optional<Extension> findByName(Extension extension);

    List<Extension> findAll();

    int delete(Long id);
}
