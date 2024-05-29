import React from "react";
import HeaderAuth from "../../components/HeaderAuth/HeaderAuth";
import '../../css/login-signup.css';

const SignUp: React.FC = () => {
    return (
        <>
            <HeaderAuth />
            <div className="container">
                <div className="forms">
                    <div className="form signup">
                        <span className="title">Registration</span>

                        <form action="#">
                            <div className="input-field">
                                <input type="text" placeholder="Enter your name" required />
                                <i className="uil uil-user"></i>
                            </div>
                            <div className="input-field">
                                <input type="text" placeholder="Enter your email" required />
                                <i className="uil uil-envelope icon"></i>
                            </div>
                            <div className="input-field">
                                <input type="password" className="password" placeholder="Create a password" required />
                                <i className="uil uil-lock icon"></i>
                            </div>
                            <div className="input-field">
                                <input type="password" className="password" placeholder="Confirm a password" required />
                                <i className="uil uil-lock icon"></i>
                                <i className="uil uil-eye-slash showHidePw"></i>
                            </div>

                            <div className="checkbox-text">
                                <div className="checkbox-content">
                                    <input type="checkbox" id="termCon" />
                                    <label htmlFor="termCon" className="text">I accepted all terms and conditions</label>
                                </div>
                            </div>

                            <div className="input-field button">
                                <input type="button" value="Signup" />
                            </div>
                        </form>

                        <div className="login-signup">
                            <span className="text">Already a member?
                                <a href="#" className="text login-link">Login Now</a>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default SignUp;