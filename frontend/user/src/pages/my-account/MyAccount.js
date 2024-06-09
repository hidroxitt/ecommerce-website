import PropTypes from "prop-types";
import React, { Fragment } from "react";
import MetaTags from "react-meta-tags";
import { Col, Row, Nav, Tab } from "react-bootstrap";
import LayoutDefault from "../../layouts/LayoutDefault";
import TabProfile from "./tabpane-user/TabProfile";
import TabSecurity from "./tabpane-user/TabSecurity";
import TabOrder from "./tabpane-user/TabOrder";
import TabPurchaseHistory from "./tabpane-user/TabPurchaseHistory";
import { multilanguage } from "redux-multilanguage";

const MyAccount = ({ strings }) => {

  return (
    <Fragment>
      <MetaTags>
        <title>ShopZone | My Account</title>
        <meta
          name="description"
          content="Compare page of ShopZone"
        />
      </MetaTags>

      <LayoutDefault headerTop="visible">

        <div className="myaccount-area pb-50 pt-50">
          <div className="container">
            <div className="row">
              <div className="ml-auto mr-auto col-lg-12">
                <div className="myaccount-wrapper">
                  <Tab.Container defaultActiveKey="first">
                    <Row>
                      <Col className="tablist-wrapper" sm={3}>
                        <Nav variant="pills" className="flex-column">
                          <Nav.Item>
                            <Nav.Link eventKey="first">{strings["profile_user"]}</Nav.Link>
                          </Nav.Item>
                          <Nav.Item>
                            <Nav.Link eventKey="second">{strings["security_user"]}</Nav.Link>
                          </Nav.Item>
                          <Nav.Item>
                            <Nav.Link eventKey="three">{strings["order_user"]}</Nav.Link>
                          </Nav.Item>
                          <Nav.Item>
                            <Nav.Link eventKey="four">{strings["purchase_history_user"]}</Nav.Link>
                          </Nav.Item>
                        </Nav>
                      </Col>
                      <Col className="tabcontent-wrapper ml-20" sm={9}>
                        <Tab.Content>
                          <Tab.Pane eventKey="first">
                            <TabProfile />
                          </Tab.Pane>
                          <Tab.Pane eventKey="second">
                            <TabSecurity />
                          </Tab.Pane>
                          <Tab.Pane eventKey="three">
                            <TabOrder />
                          </Tab.Pane>
                          <Tab.Pane eventKey="four">
                            <TabPurchaseHistory />
                          </Tab.Pane>
                        </Tab.Content>
                      </Col>
                    </Row>
                  </Tab.Container>
                </div>
              </div>
            </div>
          </div>
        </div>
      </LayoutDefault>
    </Fragment>
  );
};

MyAccount.propTypes = {
  strings: PropTypes.object
};

export default multilanguage(MyAccount);