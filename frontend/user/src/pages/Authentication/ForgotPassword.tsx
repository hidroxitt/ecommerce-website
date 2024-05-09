import React from "react";
import HeaderAuth from "../../components/HeaderAuth/HeaderAuth";
import "../../css/login-signup.css"

const ForgotPassword: React.FC = () => {
    return (
        <>
            <HeaderAuth />
            <div className="container">
                <div className="forms">
                    <div className="form login">
                        <span className="title">Bạn quên mật khẩu?</span>

                        <form action="#">
                            <div className="input-field">
                                <input type="text" placeholder="Enter your email" required />
                                <i className="uil uil-envelope icon"></i>
                            </div>

                            <div className="input-field button">
                                <input type="button" value="Tiếp tục" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
};

export default ForgotPassword;