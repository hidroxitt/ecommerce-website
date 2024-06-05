import PropTypes from "prop-types";
import React, { Fragment } from "react";
import HeaderAuth from "../wrappers/header/HeaderAuth";
import FooterDefault from "../wrappers/footer/FooterDefault";

const LayoutAuth = ({
    children,
    headerContainerClass,
    headerTop,
    headerPaddingClass
}) => {
    return (
        <Fragment>
            <HeaderAuth
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

LayoutAuth.propTypes = {
    children: PropTypes.any,
    headerContainerClass: PropTypes.string,
    headerPaddingClass: PropTypes.string,
    headerTop: PropTypes.string
};

export default LayoutAuth;
