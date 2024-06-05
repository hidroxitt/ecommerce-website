import React, { Fragment, useState } from "react";
import MetaTags from "react-meta-tags";
import { Link } from "react-router-dom";
import LayoutAuth from "../../layouts/LayoutAuth";

const Login = () => {
    const [showPassword, setShowPassword] = useState([false, false, false]);
    const togglePasswordVisibility = (index) => {
        setShowPassword((prev) =>
            prev.map((show, i) => (i === index ? !show : show))
        );
    };

    return (
        <Fragment>
            <MetaTags>
                <title>ShopZone | Login</title>
                <meta
                    name="description"
                    content="Login ShopZone"
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
                                            <h4>Login</h4>
                                        </div>
                                    </div>
                                    <div className="login-form-container">
                                        <div className="login-register-form">
                                            <form>
                                                <input
                                                    type="text"
                                                    name="user-name"
                                                    placeholder="Username"
                                                />
                                                <div className="password-input">
                                                    <input
                                                        type={showPassword[0] ? 'text' : 'password'}
                                                        className="password"
                                                        name="password"
                                                        placeholder="Password"
                                                        required
                                                    />
                                                    <i
                                                        className={`fa-regular ${showPassword[0] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                                                        onClick={() => togglePasswordVisibility(0)}
                                                    ></i>
                                                </div>
                                                <div className="button-box">
                                                    <div className="login-toggle-btn">
                                                        <input type="checkbox" />
                                                        <label className="ml-10">Remember me</label>
                                                        <Link to={process.env.PUBLIC_URL + "/forgot-password"}>
                                                            Forgot Password?
                                                        </Link>
                                                    </div>
                                                    <button type="submit">
                                                        <span>Login</span>
                                                    </button>
                                                    <div className="new-member mt-20">
                                                        <span>Not a member? {" "}</span>
                                                        <Link to={process.env.PUBLIC_URL + "/signup"}>
                                                            Signup
                                                        </Link>
                                                    </div>
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

export default Login;
