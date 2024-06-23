package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SliderRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Slider;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.SliderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SliderService {
    private final SliderRepository sliderRepository;

    public ApiResponse<Slider> createSlider(SliderRequest sliderRequest) {
        Slider slider = new Slider();
        slider.setTitle(sliderRequest.getTitle());
        slider.setSubtitle(sliderRequest.getSubtitle());
        slider.setImage(sliderRequest.getImage());
        slider.setUrl(sliderRequest.getUrl());

        sliderRepository.save(slider);
        return ApiResponse.<Slider>builder().
                code(200)
                .message("Create slider successfully")
                .result(slider)
                .build();
    }

    public ApiResponse<Slider> updateSlider(Long sliderId, SliderRequest sliderRequest) {
        Slider slider = sliderRepository.findById(sliderId).orElse(null);
        if (slider == null) {
            return ApiResponse.<Slider>builder()
                    .code(404)
                    .message("Slider not found")
                    .result(null)
                    .build();
        }
        slider.setTitle(sliderRequest.getTitle());
        slider.setSubtitle(sliderRequest.getSubtitle());
        slider.setImage(sliderRequest.getImage());
        slider.setUrl(sliderRequest.getUrl());

        sliderRepository.save(slider);
        return ApiResponse.<Slider>builder()
                .code(200)
                .message("Update slider successfully")
                .result(slider)
                .build();
    }

    public ApiResponse<Slider> getSlider(Long sliderId) {
        Slider slider = sliderRepository.findById(sliderId).orElse(null);
        if (slider == null) {
            return ApiResponse.<Slider>builder()
                    .code(404)
                    .message("Slider not found")
                    .result(null)
                    .build();
        }
        return ApiResponse.<Slider>builder()
                .code(200)
                .message("Get slider successfully")
                .result(slider)
                .build();
    }

    public ApiResponse<List<Slider>> getAllSliders() {
        return ApiResponse.<List<Slider>>builder()
                .code(200)
                .message("Get all sliders successfully")
                .result(sliderRepository.findAll())
                .build();
    }

    public ApiResponse<Slider> deleteSlider(Long sliderId) {
        Slider slider = sliderRepository.findById(sliderId).orElse(null);
        if (slider == null) {
            return ApiResponse.<Slider>builder()
                    .code(404)
                    .message("Slider not found")
                    .result(null)
                    .build();
        }
        sliderRepository.delete(slider);
        return ApiResponse.<Slider>builder()
                .code(200)
                .message("Delete slider successfully")
                .result(slider)
                .build();
    }
}
