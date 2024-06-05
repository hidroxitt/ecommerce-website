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
      <LayoutDefault headerTop="visible">
        {/* hero slider */}
        <HeroSlider />

        {/* category slider */}
        <CategorySlider spaceTopClass="pt-100" spaceBottomClass="pb-60" />

        {/* tab product */}
        <TabProduct spaceTopClass="pt-60" spaceBottomClass="pb-60" />

      </LayoutDefault>
    </Fragment>
  );
};

export default HomePage;
