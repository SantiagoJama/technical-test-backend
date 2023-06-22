package com.technicaltest.test.services;

import com.technicaltest.test.entity.Slider;
import com.technicaltest.test.handler.NotFoundException;
import com.technicaltest.test.repository.SliderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SliderService {
    private final SliderRepository sliderRepository;

    public List<Slider> list(){
        return sliderRepository.findAll();
    }

    public Slider findById(Long id) throws NotFoundException {
        return sliderRepository.findById(id).orElseThrow(() -> new NotFoundException("No se ha encontrado slider"));
    }

    public Slider findByTitle(String title) throws NotFoundException {
        return sliderRepository.findByTitle(title).orElseThrow(() -> new NotFoundException("No se ha encontrado slider con ese nombre"));
    }

    public void save(Slider slider){
        sliderRepository.save(slider);
    }

    public void update(Long id, Slider newSlider) throws NotFoundException {
        Slider sliderFound = findById(id);
        sliderFound.setTitle(newSlider.getTitle());
        sliderFound.setDescription(newSlider.getDescription());
        sliderFound.setUlrImg(newSlider.getUlrImg());
    }

    public void delete(Long id){
        sliderRepository.deleteById(id);
    }


}
