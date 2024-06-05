import PropTypes from "prop-types";
import React from "react";
import Swiper from "react-id-swiper";
import categoryData from "../../data/category/category-two.json";
import CategorySingle from "../../components/category/CategorySingle.js";
import SectionTitleTwo from "../../components/section-title/SectionTitleTwo.js";

const CategorySlider = ({ spaceTopClass, spaceBottomClass }) => {
  // swiper slider settings
  const settings = {
    loop: false,
    spaceBetween: 30,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false
    },
    breakpoints: {
      992: {
        slidesPerView: 4
      },
      576: {
        slidesPerView: 3
      },
      320: {
        slidesPerView: 1
      }
    }
  };
  return (
    <div
      className={`collections-area ${spaceTopClass ? spaceTopClass : ""}  ${spaceBottomClass ? spaceBottomClass : ""
        }`}
    >
      <div className="container">
        {/* section title */}
        <SectionTitleTwo titleText="Category" spaceBottomClass="mb-40" />
        <div className="collection-wrap">
          <div className="collection-active">
            <Swiper {...settings}>
              {categoryData &&
                categoryData.map((single, key) => {
                  return (
                    <CategorySingle
                      data={single}
                      key={key}
                      sliderClass="swiper-slide"
                    />
                  );
                })}
            </Swiper>
          </div>
        </div>
      </div>
    </div>
  );
};

CategorySlider.propTypes = {
  spaceBottomClass: PropTypes.string,
  spaceTopClass: PropTypes.string
};

export default CategorySlider;
