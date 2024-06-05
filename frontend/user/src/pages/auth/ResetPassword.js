import React, { Fragment, useState } from "react";
import MetaTags from "react-meta-tags";
import LayoutAuth from "../../layouts/LayoutAuth";

const ResetPassword = () => {
    const [showPassword, setShowPassword] = useState([false, false, false]);
    const togglePasswordVisibility = (index) => {
        setShowPassword((prev) =>
            prev.map((show, i) => (i === index ? !show : show))
        );
    };

    return (
        <Fragment>
            <MetaTags>
                <title>ShopZone | Reset Password</title>
                <meta
                    name="description"
                    content="Reset Password"
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
                                            <h4>Reset Password</h4>
                                        </div>
                                    </div>
                                    <div className="login-form-container">
                                        <div className="login-register-form">
                                            <form>
                                                <div className="password-input">
                                                    <input
                                                        type={showPassword[0] ? 'text' : 'password'}
                                                        className="password"
                                                        name="password"
                                                        placeholder=" New Password"
                                                        required
                                                    />
                                                    <i
                                                        className={`fa-regular ${showPassword[0] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                                                        onClick={() => togglePasswordVisibility(0)}
                                                    ></i>
                                                </div>
                                                <div className="password-input">
                                                    <input
                                                        type={showPassword[1] ? 'text' : 'password'}
                                                        className="password"
                                                        name="password"
                                                        placeholder="Confirm Password"
                                                        required
                                                    />
                                                    <i
                                                        className={`fa-regular ${showPassword[1] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                                                        onClick={() => togglePasswordVisibility(1)}
                                                    ></i>
                                                </div>
                                                <div className="button-box">
                                                    <button type="submit">
                                                        <span>Reset Password</span>
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

export default ResetPassword;
