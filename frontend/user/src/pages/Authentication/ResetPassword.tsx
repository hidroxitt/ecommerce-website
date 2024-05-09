import React from "react";
import HeaderAuth from "../../components/HeaderAuth/HeaderAuth";
import "../../css/login-signup.css";

const ResetPassword: React.FC = () => {
    return (
        <>
            <HeaderAuth />
            <div className="container">
                <div className="forms">
                    <div className="form login">
                        <span className="title">Tạo mật khẩu mới</span>

                        <form action="#">
                            <div className="input-field">
                                <input type="password" className="password" placeholder="Enter your password" required />
                                <i className="uil uil-lock icon"></i>
                                <i className="uil uil-eye-slash showHidePw"></i>
                            </div>
                            <div className="input-field">
                                <input type="password" className="password" placeholder="Enter your password" required />
                                <i className="uil uil-lock icon"></i>
                                <i className="uil uil-eye-slash showHidePw"></i>
                            </div>

                            <div className="input-field button">
                                <input type="button" value="Hoàn thành" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
};

export default ResetPassword;