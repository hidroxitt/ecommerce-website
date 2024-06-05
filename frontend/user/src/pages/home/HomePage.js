import React, { Fragment } from "react";
import MetaTags from "react-meta-tags";
import LayoutDefault from "../../layouts/LayoutDefault";
import HeroSlider from "../../wrappers/hero-slider/HeroSlider";
import TabProduct from "../../wrappers/product/TabProduct";
import CategorySlider from "../../wrappers/category/CategorySlider";

const HomePage = () => {
  return (
    <Fragment>
      <MetaTags>
        <title>ShopZone | Home</title>
        <meta
          name="description"
          content="ShopZone eCommerce"
        />
      </MetaTags>
      <LayoutDefault>
        {/* hero slider */}
        <HeroSlider />

        {/* category slider */}
        <CategorySlider spaceTopClass="pt-100" spaceBottomClass="pb-60" category="fashion" />

        {/* tab product */}
        <TabProduct spaceTopClass="pt-60" spaceBottomClass="pb-60" category="fashion" />

      </LayoutDefault>
    </Fragment>
  );
};

export default HomePage;
