import PropTypes from "prop-types";
import React from "react"
import { multilanguage } from "redux-multilanguage";

const SectionTitleTwo = ({ strings, spaceBottomClass }) => {
  return (
    <div
      className={`section-title-3 ${spaceBottomClass ? spaceBottomClass : ""}`}
    >
      <h4>{strings["category_title"]}</h4>
    </div>
  );
};

SectionTitleTwo.propTypes = {
  strings: PropTypes.object,
  spaceBottomClass: PropTypes.string,
};

export default multilanguage(SectionTitleTwo);
