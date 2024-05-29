// import PropTypes from "prop-types";
import React, { Fragment } from "react";
import MetaTags from "react-meta-tags";
import { Link } from "react-router-dom";
import LayoutAuth from "../../layouts/LayoutAuth";

const ForgotPassword = () => {
    return (
        <Fragment>
            <MetaTags>
                <title>ShopZone | Forgot Password</title>
                <meta
                    name="description"
                    content="Fashion home of flone react minimalist eCommerce template."
                />
            </MetaTags>
            <LayoutAuth headerTop="visible">
                <div className="auth-container">
                    <div className="auth-wrapper">
                        <div className="auth-component">
                            <div className="auth-form forgot-password">
                                <span className="title">Forgot Password</span>

                                <form action="#">
                                    <div className="input-field">
                                        <input type="text" placeholder="Enter your email" required />
                                        <i className="fa-regular fa-envelope"></i>
                                    </div>

                                    <div className="input-field auth-button">
                                        <input type="button" value="Send Reset Link" />
                                    </div>
                                </form>

                                <div className="login-signup">
                                    <span className="text">Remember your password?
                                        <Link to="/login-register" className="text login-link">Login Now</Link>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </LayoutAuth>
        </Fragment>
    );
};

export default ForgotPassword;