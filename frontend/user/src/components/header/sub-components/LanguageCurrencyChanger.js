import PropTypes from "prop-types";
import React from "react";
import { changeLanguage, multilanguage } from "redux-multilanguage";
import { Link } from "react-router-dom";

const LanguageCurrencyChanger = ({
  strings,
  currency,
  changeCurrency,
  currentLanguageCode,
  dispatch
}) => {
  const changeLanguageTrigger = e => {
    const languageCode = e.target.value;
    dispatch(changeLanguage(languageCode));
  };

  const changeCurrencyTrigger = e => {
    const currencyName = e.target.value;
    changeCurrency(currencyName);
  };

  return (
    <div className="language-currency-wrap">
      <div className="same-language-currency language-style">
        <span>
          {currentLanguageCode === "en"
            ? "English"
            : currentLanguageCode === "vn"
              ? "Vietnamese"
              : ""}{" "}
          <i className="fa-regular fa-angle-down"></i>
        </span>
        <div className="lang-car-dropdown">
          <ul>
            <li>
              <button value="en" onClick={e => changeLanguageTrigger(e)}>
                English
              </button>
            </li>
            <li>
              <button value="vn" onClick={e => changeLanguageTrigger(e)}>
                Vietnamese
              </button>
            </li>
          </ul>
        </div>
      </div>
      <div className="same-language-currency use-style">
        <span>
          {currency.currencyName} <i className="fa-regular fa-angle-down"></i>
        </span>
        <div className="lang-car-dropdown">
          <ul>
            <li>
              <button value="USD" onClick={e => changeCurrencyTrigger(e)}>
                USD
              </button>
            </li>
            <li>
              <button value="EUR" onClick={e => changeCurrencyTrigger(e)}>
                EUR
              </button>
            </li>
            <li>
              <button value="GBP" onClick={e => changeCurrencyTrigger(e)}>
                GBP
              </button>
            </li>
          </ul>
        </div>
      </div>
      <div className="same-language-currency">
        <Link to="/contact">{strings["start_selling"]}</Link>
      </div>
      <div className="same-language-currency">
        <Link to="/contact">{strings["contact_us"]}</Link>
      </div>
    </div>
  );
};

LanguageCurrencyChanger.propTypes = {
  strings: PropTypes.object,
  changeCurrency: PropTypes.func,
  currency: PropTypes.object,
  currentLanguageCode: PropTypes.string,
  dispatch: PropTypes.func
};

export default multilanguage(LanguageCurrencyChanger);
