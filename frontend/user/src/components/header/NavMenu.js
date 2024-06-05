import PropTypes from "prop-types";
import React from "react";
import { multilanguage } from "redux-multilanguage";

const NavMenu = ({ menuWhiteClass, sidebarMenu }) => {
  return (
    <div
      className={` ${sidebarMenu
        ? "sidebar-menu"
        : `main-menu ${menuWhiteClass ? menuWhiteClass : ""}`
        } `}
    >
      <div className="search-content">
        <form action="#">
          <input type="text" placeholder="Search" />
          <button className="button-search">
            <i className="fa-light fa-magnifying-glass"></i>
          </button>
        </form>
      </div>
    </div>
  );
};

NavMenu.propTypes = {
  menuWhiteClass: PropTypes.string,
  sidebarMenu: PropTypes.bool
};

export default multilanguage(NavMenu);
