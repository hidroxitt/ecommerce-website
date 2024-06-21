package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SliderRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Slider;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.SliderService;

import java.util.List;

@RestController
@RequestMapping("/api/slider")
@RequiredArgsConstructor
public class SliderController {
    private final SliderService sliderService;

    @PostMapping("/create")
    public ApiResponse<Slider> createSlider(@RequestBody SliderRequest sliderRequest) {
        return sliderService.createSlider(sliderRequest);
    }

    @PostMapping("/update/{sliderId}")
    public ApiResponse<Slider> updateSlider(@PathVariable Long sliderId, @RequestBody SliderRequest sliderRequest) {
        return sliderService.updateSlider(sliderId, sliderRequest);
    }

    @PostMapping("/get-all")
    public ApiResponse<List<Slider>> getAllSlider() {
        return sliderService.getAllSlider();
    }

    @PostMapping("/get/{sliderId}")
    public ApiResponse<Slider> getSlider(@PathVariable Long sliderId) {
        return sliderService.getSlider(sliderId);
    }

    @PostMapping("/delete/{sliderId}")
    public ApiResponse<Slider> deleteSlider(@PathVariable Long sliderId) {
        return sliderService.deleteSlider(sliderId);
    }
}
