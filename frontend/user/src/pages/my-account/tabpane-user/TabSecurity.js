import PropTypes from "prop-types";
import React, { Fragment, useState } from "react";
import { multilanguage } from "redux-multilanguage";

const TabSecurity = ({ strings }) => {
    const [showPassword, setShowPassword] = useState([false, false, false]);
    const togglePasswordVisibility = (index) => {
        setShowPassword((prev) =>
            prev.map((show, i) => (i === index ? !show : show))
        );
    };

    return (
        <Fragment>
            <div className="myaccount-profile-wrapper">
                <h4>{strings["change_password"]}</h4>
                <div className="input-profile-area">
                    <label>{strings["old_password"]}</label>
                    <div className="password-wrapper">
                        <input type={showPassword[0] ? 'text' : 'password'} className="change-password" name="password" />
                        <i
                            className={`fa-regular ${showPassword[0] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                            onClick={() => togglePasswordVisibility(0)}
                        ></i>
                    </div>
                </div>
                <div className="input-profile-area">
                    <label>{strings["new_password"]}</label>
                    <div className="password-wrapper">
                        <input type={showPassword[1] ? 'text' : 'password'} className="change-password" name="password" />
                        <i
                            className={`fa-regular ${showPassword[1] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                            onClick={() => togglePasswordVisibility(1)}
                        ></i>
                    </div>
                </div>
                <div className="input-profile-area">
                    <label>{strings["confirm_new_password"]}</label>
                    <div className="password-wrapper">
                        <input type={showPassword[2] ? 'text' : 'password'} className="change-password" name="password" />
                        <i
                            className={`fa-regular ${showPassword[2] ? 'fa-eye' : 'fa-eye-slash'} showHidePw`}
                            onClick={() => togglePasswordVisibility(2)}
                        ></i>
                    </div>
                </div>
            </div>
        </Fragment>
    );
};

TabSecurity.propTypes = {
    strings: PropTypes.object
};

export default multilanguage(TabSecurity);