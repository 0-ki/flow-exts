package com.homework.flow.exts.service.extension;

import com.homework.flow.exts.domain.Extension;
import com.homework.flow.exts.repository.extension.ExtsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Transactional
@Service
@RequiredArgsConstructor
public class ExtsServiceImpl implements ExtsService {

    private final ExtsRepository repository;

    @Override
    public Extension saveCustom(Extension extension) {
        if(repository.getCountCustom() > 199) throw new IllegalStateException("확장자는 최대 200개까지 등록 가능합니다");
        validate(extension);
        Optional<Extension> extsByName = repository.findByName( extension.getName());
        if(extsByName.isPresent()) throw new IllegalStateException("이미 등록된 확장자 입니다");
        return repository.save(extension);
    }

    @Override
    public int update(Extension extension) {
        validate(extension);
        return repository.update(extension);
    }

    @Override
    public Optional<Extension> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Extension> findByName(Extension extension) {
        return repository.findByName(extension.getName());
    }

    @Override
    public List<Extension> findAll() {
        return repository.findAll();
    }

    @Override
    public int delete(Long id) {
        return repository.delete(id);
    }

    private void validate(Extension extension) {
        String targetName = extension.getName();
        if( targetName.length() > 20) throw new IllegalArgumentException("확장자는 최대 20자리까지 작성 가능합니다");
        if( ! Pattern.matches("^[a-zA-Z]*$", targetName)) throw new IllegalArgumentException("확장자는 영문으로만 작성해야 합니다");
    }
}
