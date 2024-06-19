import React, { Fragment } from "react";
import MetaTags from "react-meta-tags";
import LayoutDefault from "../../layouts/LayoutDefault";
import SectionTitleWithText from "../../components/section-title/SectionTitleWithText";
import TextGridOne from "../../wrappers/text-grid/TextGridOne";

const About = () => {

  return (
    <Fragment>
      <MetaTags>
        <title>ShopZone | About us</title>
        <meta
          name="description"
          content="About page of ShopZone"
        />
      </MetaTags>

      <LayoutDefault headerTop="visible">

        {/* section title with text */}
        <SectionTitleWithText spaceTopClass="pt-100" spaceBottomClass="pb-95" />

        {/* text grid */}
        <TextGridOne spaceBottomClass="pb-70" />

      </LayoutDefault>
    </Fragment>
  );
};

export default About;
