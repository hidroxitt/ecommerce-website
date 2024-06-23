package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SliderRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Slider;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.SliderService;

import java.util.List;

@RestController
@RequestMapping("/slider")
@RequiredArgsConstructor
public class SliderController {
    private final SliderService sliderService;

    @PostMapping("/create")
    public ApiResponse<Slider> createSlider(SliderRequest sliderRequest) {
        return sliderService.createSlider(sliderRequest);
    }

    @PostMapping("/update")
    public ApiResponse<Slider> updateSlider(Long sliderId, SliderRequest sliderRequest) {
        return sliderService.updateSlider(sliderId, sliderRequest);
    }

    @PostMapping("/get")
    public ApiResponse<Slider> getSlider(Long sliderId) {
        return sliderService.getSlider(sliderId);
    }

    @PostMapping("/getAll")
    public ApiResponse<List<Slider>> getAllSliders() {
        return sliderService.getAllSliders();
    }

    @PostMapping("/delete")
    public ApiResponse<Slider> deleteSlider(Long sliderId) {
        return sliderService.deleteSlider(sliderId);
    }
}
