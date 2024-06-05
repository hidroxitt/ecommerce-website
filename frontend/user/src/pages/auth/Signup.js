import React, { Fragment, useState } from "react";
import MetaTags from "react-meta-tags";
import LayoutAuth from "../../layouts/LayoutAuth";

const Signup = () => {
    const [showPassword, setShowPassword] = useState([false, false, false]);
    const togglePasswordVisibility = (index) => {
        setShowPassword((prev) =>
            prev.map((show, i) => (i === index ? !show : show))
        );
    };

    return (
        <Fragment>
            <MetaTags>
                <title>ShopZone | Signup</title>
                <meta
                    name="description"
                    content="Signup ShopZone"
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
                                            <h4>Signup</h4>
                                        </div>
                                    </div>
                                    <div className="login-form-container">
                                        <div className="login-register-form">
                                            <form>
                                                <input
                                                    name="user-email"
                                                    placeholder="Email"
                                                    type="email"
                                                />
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
                                                <div className="button-box mb-20">
                                                    <div className="login-toggle-btn">
                                                        <input type="checkbox" />
                                                        <label className="ml-10">I accepted all terms and conditions</label>
                                                    </div>
                                                    <button type="submit">
                                                        <span>Register</span>
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

export default Signup;
