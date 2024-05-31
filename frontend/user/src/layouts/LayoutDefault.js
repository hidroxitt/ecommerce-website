import PropTypes from "prop-types";
import React, { Fragment } from "react";
import HeaderDefault from "../wrappers/header/HeaderDefault";
import FooterDefault from "../wrappers/footer/FooterDefault";

const LayoutDefault = ({
  children,
  headerContainerClass,
  headerTop,
  headerPaddingClass
}) => {
  return (
    <Fragment>
      <HeaderDefault
        layout={headerContainerClass}
        top={headerTop}
        headerPaddingClass={headerPaddingClass}
      />
      {children}
      <FooterDefault
        backgroundColorClass="bg-gray"
        spaceTopClass="pt-100"
        spaceBottomClass="pb-70"
      />
    </Fragment>
  );
};

LayoutDefault.propTypes = {
  children: PropTypes.any,
  headerContainerClass: PropTypes.string,
  headerPaddingClass: PropTypes.string,
  headerTop: PropTypes.string
};

export default LayoutDefault;
