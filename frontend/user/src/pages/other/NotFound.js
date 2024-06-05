import React, { Fragment } from "react";
import { Link } from "react-router-dom";
import MetaTags from "react-meta-tags";
import LayoutDefault from "../../layouts/LayoutDefault";

const NotFound = () => {

  return (
    <Fragment>
      <MetaTags>
        <title>ShopZone | Not Found</title>
        <meta
          name="description"
          content="404 page of ShopZone"
        />
      </MetaTags>

      <LayoutDefault headerTop="visible">

        <div className="error-area pt-40 pb-100">
          <div className="container">
            <div className="row justify-content-center">
              <div className="col-xl-7 col-lg-8 text-center">
                <div className="error">
                  <h1>404</h1>
                  <h2>OPPS! PAGE NOT FOUND</h2>
                  <p>
                    Sorry but the page you are looking for does not exist, have
                    been removed, name changed or is temporarity unavailable.
                  </p>
                  <form className="searchform mb-50">
                    <input
                      type="text"
                      name="search"
                      id="error_search"
                      placeholder="Search..."
                      className="searchform__input"
                    />
                    <button type="submit" className="searchform__submit">
                      <i className="fa-light fa-magnifying-glass"></i>
                    </button>
                  </form>
                  <Link to={process.env.PUBLIC_URL + "/"} className="error-btn">
                    Back to home page
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </LayoutDefault>
    </Fragment>
  );
};

export default NotFound;
