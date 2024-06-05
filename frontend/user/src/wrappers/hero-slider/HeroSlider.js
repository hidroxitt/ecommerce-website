import React from "react";
import Swiper from "react-id-swiper";
import heroSliderData from "../../data/hero-sliders/hero-slider-one.json";
import HeroSliderSingle from "../../components/hero-slider/HeroSliderSingle.js";

const HeroSlider = () => {
  const params = {
    effect: "fade",
    loop: true,
    speed: 1000,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false
    },
    watchSlidesVisibility: true,
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev"
    },
    renderPrevButton: () => (
      <button className="swiper-button-prev ht-swiper-button-nav">
        <i className="fa-light fa-angle-left"></i>
      </button>
    ),
    renderNextButton: () => (
      <button className="swiper-button-next ht-swiper-button-nav">
        <i className="fa-light fa-angle-right"></i>
      </button>
    )
  };

  return (
    <div className="slider-area">
      <div className="slider-active nav-style-1">
        <Swiper {...params}>
          {heroSliderData &&
            heroSliderData.map((single, key) => {
              return (
                <HeroSliderSingle
                  sliderClassName="swiper-slide"
                  data={single}
                  key={key}
                />
              );
            })}
        </Swiper>
      </div>
    </div>
  );
};

export default HeroSlider;
