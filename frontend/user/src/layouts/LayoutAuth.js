import PropTypes from "prop-types";
import React, { Fragment } from "react";
import HeaderOne from "../wrappers/header/HeaderAuth";

const LayoutAuth = ({
    children,
    headerContainerClass,
    headerTop,
    headerPaddingClass
}) => {
    return (
        <Fragment>
            <HeaderOne
                layout={headerContainerClass}
                top={headerTop}
                headerPaddingClass={headerPaddingClass}
            />
            {children}
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
