import React, { Fragment } from "react";
import MetaTags from "react-meta-tags";
import LayoutOne from "../../layouts/LayoutOne";
import HeroSliderTen from "../../wrappers/hero-slider/HeroSliderTen";
import CategoryThreeSlider from "../../wrappers/category/CategoryThreeSlider";
import TabProductTen from "../../wrappers/product/TabProductTen";

const HomePage = () => {
  return (
    <Fragment>
      <MetaTags>
        <title>ShopZone | Fashion Home</title>
        <meta
          name="description"
          content="Fashion home of flone react minimalist eCommerce template."
        />
      </MetaTags>
      <LayoutOne
        headerContainerClass="container-fluid"
        headerPaddingClass="header-padding-2"
        headerTop="visible"
      >
        {/* hero slider */}
        <HeroSliderTen />
        {/* category */}
        <CategoryThreeSlider spaceTopClass="pt-100" spaceBottomClass="pb-80" />
        {/* tab product */}
        <TabProductTen
          spaceBottomClass="pb-60"
          spaceTopClass="pt-60"
          category="electronics"
        />
      </LayoutOne>
    </Fragment>
  );
};

export default HomePage;
