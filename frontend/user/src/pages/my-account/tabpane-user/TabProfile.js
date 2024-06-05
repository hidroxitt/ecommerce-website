import PropTypes from "prop-types";
import React, { Fragment } from "react";
import { multilanguage } from "redux-multilanguage";

const TabProfile = ({ strings }) => {
    return (
        <Fragment>
            <div className="myaccount-profile-wrapper">
                <h4>{strings["personal_information"]}</h4>
                <div className="input-profile-area">
                    <label>{strings["full_name"]}</label>
                    <input className="full-name" type="text" />
                </div>
                <div className="input-profile-area">
                    <label>{strings["date_of_birth"]}</label>
                    <input className="date-of-birth" type="date" />
                </div>
                <div className="input-profile-area">
                    <label>{strings["username"]}</label>
                    <input className="username-profile" type="text" />
                </div>
                <div className="input-profile-area">
                    <label>{strings["email"]}</label>
                    <input className="email-profile" type="text" />
                </div>
                <div className="input-profile-area">
                    <label>{strings["number_phone"]}</label>
                    <input className="phone-profile" type="text" />
                </div>
                <div className="input-profile-area">
                    <label>{strings["address"]}</label>
                    <input className="addresss-profile" type="text" />
                </div>
            </div>
        </Fragment>
    );
};

TabProfile.propTypes = {
    strings: PropTypes.object
};

export default multilanguage(TabProfile);