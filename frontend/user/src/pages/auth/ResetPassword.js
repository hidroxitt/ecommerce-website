import React, { Fragment, useState } from "react";
import MetaTags from "react-meta-tags";
import { Link } from "react-router-dom";
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
            <MetaTags
                title="ShopZone | Reset Password"
                description="Fashion home of flone react minimalist eCommerce template."
            />
            <LayoutAuth headerTop="visible">
                <div className="auth-container">
                    <div className="auth-wrapper">
                        <div className="auth-component">
                            <div className="auth-form reset-password">
                                <span className="title">Reset Password</span>

                                <form action="#">
                                    <div className="input-field">
                                        <input
                                            type={showPassword[0] ? 'text' : 'password'}
                                            className="password"
                                            placeholder="Enter new password"
                                            name="password"
                                            required
                                        />
                                        <i className="fa-regular fa-lock"></i>
                                        <i
                                            className={`fa-regular ${showPassword[0] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                                            onClick={() => togglePasswordVisibility(0)}
                                        ></i>
                                    </div>
                                    <div className="input-field">
                                        <input
                                            type={showPassword[1] ? 'text' : 'password'}
                                            className="password"
                                            placeholder="Confirm new password"
                                            name="password"
                                            required
                                        />
                                        <i className="fa-regular fa-lock"></i>
                                        <i
                                            className={`fa-regular ${showPassword[1] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                                            onClick={() => togglePasswordVisibility(1)}
                                        ></i>
                                    </div>

                                    <div className="input-field auth-button">
                                        <input type="button" value="Reset Password" />
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

export default ResetPassword;