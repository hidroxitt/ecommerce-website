import PropTypes from "prop-types";
import React, { Fragment } from "react";

const ProductRating = ({ ratingValue }) => {
  let rating = [];

  for (let i = 0; i < 5; i++) {
    rating.push(<i className="fa-light fa-star" key={i}></i>);
  }
  if (ratingValue && ratingValue > 0) {
    for (let i = 0; i <= ratingValue - 1; i++) {
      rating[i] = <i className="fa-solid fa-star" style={{color: "#FFD43B"}} key={i}></i>;
    }
  }
  return <Fragment>{rating}</Fragment>;
};

ProductRating.propTypes = {
  ratingValue: PropTypes.number
};

export default ProductRating;
