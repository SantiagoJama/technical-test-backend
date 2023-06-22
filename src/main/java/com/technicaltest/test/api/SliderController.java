package com.technicaltest.test.api;

import com.technicaltest.test.entity.Slider;
import com.technicaltest.test.handler.NotFoundException;
import com.technicaltest.test.services.SliderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slide")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SliderController {

    private final SliderService sliderService;

    @GetMapping
    public ResponseEntity<List<Slider>> list() {
        return ResponseEntity.ok(sliderService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slider> getById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(sliderService.findById(id));
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody Slider sliderDto) {
        sliderService.save(sliderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Slider sliderDto) throws NotFoundException {
        sliderService.update(id, sliderDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        sliderService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
