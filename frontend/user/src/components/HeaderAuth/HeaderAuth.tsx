import React from "react";
import Logo from '../../images/logo/logo-3.png';
import '../../css/login-signup.css';

const HeaderAuth: React.FC = () => {
    return (
        <div className="main-wrapper bg-gray-9">
            <header className="header-area bg-blue">
                <div className="header-large-device">
                    <div className="header-top header-top-ptb-7">

                        <div className="row align-items-center">
                            <div className="col-xl-2 col-lg-2 pt-50 pb-10 pl-390">
                                <div className="logo">
                                    <a href="index.html"><img src={Logo} alt="logo" /></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="header-small-device small-device-ptb-1">
                    <div className="row align-items-center">
                        <div className="col-5">
                            <div className="mobile-logo">
                                <a href="index.html">
                                    <img alt="" src={Logo} />
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </div>
    );
};

export default HeaderAuth;