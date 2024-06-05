import React, { Fragment } from "react";
import MetaTags from "react-meta-tags";
import LayoutAuth from "../../layouts/LayoutAuth";

const ForgotPassword = () => {

    return (
        <Fragment>
            <MetaTags>
                <title>ShopZone | Forgot Password</title>
                <meta
                    name="description"
                    content="Forgot Password"
                />
            </MetaTags>

            <LayoutAuth headerTop="visible">
                <div className="login-register-area pt-60 pb-100">
                    <div className="container">
                        <div className="row">
                            <div className="col-lg-4 col-md-10 ml-auto mr-auto">
                                <div className="login-register-wrapper pt-40">
                                    <div className="login-register-tab-list">
                                        <div className="nav-item">
                                            <h4>Forgot Password</h4>
                                        </div>
                                    </div>
                                    <div className="login-form-container">
                                        <div className="login-register-form">
                                            <form>
                                                <input
                                                    type="email"
                                                    name="email"
                                                    placeholder="Enter your email"
                                                    required
                                                />
                                                <div className="button-box">
                                                    <button type="submit">
                                                        <span>Send Reset Link</span>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
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
