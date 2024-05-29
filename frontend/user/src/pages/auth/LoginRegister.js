import PropTypes from "prop-types";
import React, { Fragment, useState } from "react";
import MetaTags from "react-meta-tags";
import { Link } from "react-router-dom";
import LayoutAuth from "../../layouts/LayoutAuth";

const LoginRegister = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [showPassword, setShowPassword] = useState([false, false, false]);
    const [formData, setFormData] = useState({
        email: '',
        password: '',
        name: '',
        confirmPassword: '',
        remember: false,
        terms: false
    });

    const toggleAuthMode = () => {
        setIsLogin(!isLogin);
    };

    const togglePasswordVisibility = (index) => {
        setShowPassword((prev) =>
            prev.map((show, i) => (i === index ? !show : show))
        );
    };

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    return (
        <Fragment>
            <MetaTags>
                <title>ShopZone | {isLogin ? "Login" : "Signup"}</title>
                <meta
                    name="description"
                    content="Compare page of flone react minimalist eCommerce template."
                />
            </MetaTags>

            <LayoutAuth headerTop="visible">
                <div className="auth-container">
                    <div className={`auth-wrapper ${isLogin ? "" : "active"}`}>
                        <div className="auth-component">
                            {isLogin ? (
                                <div className="auth-form login">
                                    <span className="title">Login</span>

                                    <form action="#">
                                        <div className="input-field">
                                            <input
                                                type="text"
                                                placeholder="Enter your email"
                                                name="email"
                                                value={formData.email}
                                                onChange={handleChange}
                                                required
                                            />
                                            <i className="fa-regular fa-user"></i>
                                        </div>
                                        <div className="input-field">
                                            <input
                                                type={showPassword[0] ? 'text' : 'password'}
                                                className="password"
                                                placeholder="Enter your password"
                                                name="password"
                                                value={formData.password}
                                                onChange={handleChange}
                                                required
                                            />
                                            <i className="fa-regular fa-lock"></i>
                                            <i
                                                className={`fa-regular ${showPassword[0] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                                                onClick={() => togglePasswordVisibility(0)}
                                            ></i>
                                        </div>

                                        <div className="checkbox-text">
                                            <div className="checkbox-content">
                                                <input
                                                    type="checkbox"
                                                    id="logCheck"
                                                    name="remember"
                                                    checked={formData.remember}
                                                    onChange={handleChange}
                                                />
                                                <label htmlFor="logCheck" className="text">Remember me</label>
                                            </div>

                                            <Link to="/forgot-password" className="text">Forgot password?</Link>
                                        </div>

                                        <div className="input-field auth-button">
                                            <input type="button" value="Login" />
                                        </div>
                                    </form>

                                    <div className="login-signup">
                                        <span className="text">Not a member?
                                            <Link to="#" onClick={toggleAuthMode} className="text signup-link">Signup Now</Link>
                                        </span>
                                    </div>
                                </div>
                            ) : (
                                <div className="auth-form signup">
                                    <span className="title">Registration</span>

                                    <form action="#">
                                        <div className="input-field">
                                            <input
                                                type="text"
                                                placeholder="Enter your name"
                                                name="name"
                                                value={formData.name}
                                                onChange={handleChange}
                                                required
                                            />
                                            <i className="fa-regular fa-user"></i>
                                        </div>
                                        <div className="input-field">
                                            <input
                                                type="text"
                                                placeholder="Enter your email"
                                                name="email"
                                                value={formData.email}
                                                onChange={handleChange}
                                                required
                                            />
                                            <i className="fa-regular fa-envelope"></i>
                                        </div>
                                        <div className="input-field">
                                            <input
                                                type={showPassword[1] ? 'text' : 'password'}
                                                className="password"
                                                placeholder="Create a password"
                                                name="password"
                                                value={formData.password}
                                                onChange={handleChange}
                                                required
                                            />
                                            <i className="fa-regular fa-lock"></i>
                                            <i
                                                className={`fa-regular ${showPassword[1] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                                                onClick={() => togglePasswordVisibility(1)}
                                            ></i>
                                        </div>
                                        <div className="input-field">
                                            <input
                                                type={showPassword[2] ? 'text' : 'password'}
                                                className="password"
                                                placeholder="Confirm your password"
                                                name="confirmPassword"
                                                value={formData.confirmPassword}
                                                onChange={handleChange}
                                                required
                                            />
                                            <i className="fa-regular fa-lock icon"></i>
                                            <i
                                                className={`fa-regular ${showPassword[2] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                                                onClick={() => togglePasswordVisibility(2)}
                                            ></i>
                                        </div>

                                        <div className="checkbox-text">
                                            <div className="checkbox-content">
                                                <input
                                                    type="checkbox"
                                                    id="termCon"
                                                    name="terms"
                                                    checked={formData.terms}
                                                    onChange={handleChange}
                                                />
                                                <label htmlFor="termCon" className="text">I accepted all terms and conditions</label>
                                            </div>
                                        </div>

                                        <div className="input-field auth-button">
                                            <input type="button" value="Signup" />
                                        </div>
                                    </form>

                                    <div className="login-signup">
                                        <span className="text">Already a member?
                                            <Link to="#" onClick={toggleAuthMode} className="text login-link">Login Now</Link>
                                        </span>
                                    </div>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </LayoutAuth>
        </Fragment>
    );
};

LoginRegister.propTypes = {
    location: PropTypes.object
};

export default LoginRegister;
