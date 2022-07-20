package com.homework.flow.exts.web;

import com.homework.flow.exts.domain.Extension;
import com.homework.flow.exts.service.extension.ExtsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExtsController {

    private final ExtsService service;
    @GetMapping("/exts")
    public ResponseEntity<?> getAllExtensions() {

        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/exts/{ext}")
    public ResponseEntity<?> saveCustomExtension(@PathVariable(required = true) String ext) {
        Extension addExt = com.homework.flow.exts.domain.Extension.builder()
                .name(ext)
                .flagFixed(false)
                .flagUse(true)
                .build();
        return ResponseEntity.ok().body( service.saveCustom(addExt));
    }

    @PutMapping("/exts/{id}")
    public ResponseEntity<?> edit(@PathVariable(required = true)Long id, @RequestBody Extension extension) {
        log.info("extension={}", extension);
        int isSuccess = service.update(extension);
        return ResponseEntity.ok().body("{"+ (isSuccess > 0) +"}");
    }

    @DeleteMapping("/exts/{id}")
    public ResponseEntity<?> delete(@PathVariable(required = true)Long id) {
        int isSuccess = service.delete( id);
        return ResponseEntity.ok().body("{"+ (isSuccess > 0) +"}");
    }

}
