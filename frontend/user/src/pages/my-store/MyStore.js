import PropTypes from "prop-types";
import React, { Fragment } from "react";
import MetaTags from "react-meta-tags";
import { Col, Row, Nav, Tab } from "react-bootstrap";
import LayoutDefault from "../../layouts/LayoutDefault";
import TabProductManagement from "./tabpane-seller/TabProductManagement";
import TabOrderManagement from "./tabpane-seller/TabOrderManagement";
import TabPurchaseHistory from "../my-account/tabpane-user/TabPurchaseHistory";
import TabStockManagement from "./tabpane-seller/TabStockManagement";
import { multilanguage } from "redux-multilanguage";

const MyStore = ({ strings }) => {

    return (
        <Fragment>
            <MetaTags>
                <title>ShopZone | My Store</title>
                <meta
                    name="description"
                    content="Compare page of ShopZone"
                />
            </MetaTags>

            <LayoutDefault headerTop="visible">

                <div className="mystore-area pb-50 pt-50">
                    <div className="container">
                        <div className="row">
                            <div className="ml-auto mr-auto col-lg-12">
                                <div className="mystore-wrapper">
                                    <Tab.Container defaultActiveKey="first">
                                        <Row>
                                            <Col className="tablist-wrapper" sm={3}>
                                                <Nav variant="pills" className="flex-column">
                                                    <Nav.Item>
                                                        <Nav.Link eventKey="first">{strings["product_management"]}</Nav.Link>
                                                    </Nav.Item>
                                                    <Nav.Item>
                                                        <Nav.Link eventKey="second">{strings["order_management"]}</Nav.Link>
                                                    </Nav.Item>
                                                    <Nav.Item>
                                                        <Nav.Link eventKey="three">{strings["voucher_management"]}</Nav.Link>
                                                    </Nav.Item>
                                                    <Nav.Item>
                                                        <Nav.Link eventKey="four">{strings["stock_management"]}</Nav.Link>
                                                    </Nav.Item>
                                                </Nav>
                                            </Col>
                                            <Col className="tabcontent-wrapper ml-20" sm={9}>
                                                <Tab.Content>
                                                    <Tab.Pane eventKey="first">
                                                        <TabProductManagement />
                                                    </Tab.Pane>
                                                    <Tab.Pane eventKey="second">
                                                        <TabOrderManagement />
                                                    </Tab.Pane>
                                                    <Tab.Pane eventKey="three">
                                                        <TabPurchaseHistory />
                                                    </Tab.Pane>
                                                    <Tab.Pane eventKey="four">
                                                        <TabStockManagement />
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

MyStore.propTypes = {
    strings: PropTypes.object
};

export default multilanguage(MyStore);
