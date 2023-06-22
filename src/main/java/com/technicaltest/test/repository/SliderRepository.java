package com.technicaltest.test.repository;

import com.technicaltest.test.entity.Slider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SliderRepository extends JpaRepository<Slider, Long> {

    Optional<Slider> findByTitle(String title);
}
