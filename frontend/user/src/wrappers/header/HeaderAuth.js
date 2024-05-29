import PropTypes from "prop-types";
import React, { useEffect, useState } from "react";
import Logo from "../../components/header/Logo";

const HeaderOne = ({
    layout,
    top,
    borderStyle,
    headerPaddingClass,
    headerBgClass
}) => {
    const [scroll, setScroll] = useState(0);
    const [headerTop, setHeaderTop] = useState(0);

    useEffect(() => {
        const header = document.querySelector(".sticky-bar");
        setHeaderTop(header.offsetTop);
        window.addEventListener("scroll", handleScroll);
        return () => {
            window.removeEventListener("scroll", handleScroll);
        };
    }, []);

    const handleScroll = () => {
        setScroll(window.scrollY);
    };

    return (
        <header
            className={`header-area clearfix ${headerBgClass ? headerBgClass : ""}`}
        >
            <div
                className={`${headerPaddingClass ? headerPaddingClass : ""} ${top === "visible" ? "d-none d-lg-block" : "d-none"
                    } header-top-area ${borderStyle === "fluid-border" ? "border-none" : ""
                    }`}
            >
            </div>

            <div
                className={` ${headerPaddingClass ? headerPaddingClass : ""
                    } sticky-bar header-res-padding clearfix ${scroll > headerTop ? "stick" : ""
                    }`}
            >
                <div className={layout === "container-fluid" ? layout : "container"}>
                    <div className="row">
                        <div className="col-xl-2 col-lg-2 col-md-6 col-4">
                            {/* header logo */}
                            <Logo imageUrl="/assets/img/logo/logo.png" logoClass="logo" />
                        </div>
                    </div>
                </div>
            </div>
        </header>
    );
};

HeaderOne.propTypes = {
    borderStyle: PropTypes.string,
    headerPaddingClass: PropTypes.string,
    layout: PropTypes.string,
    top: PropTypes.string
};

export default HeaderOne;
