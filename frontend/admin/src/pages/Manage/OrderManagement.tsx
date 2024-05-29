import React from "react";
import DefaultLayout from "../../layout/DefaultLayout";
import TableOrder from "../../components/Tables/TableOrder";
import OrderModal from "../Modal/OrderModal";

const OrderManagement: React.FC = () => {
    return (
        <DefaultLayout>
            <div className="flex flex-col gap-10">
                <OrderModal />
                <TableOrder />
            </div>
        </DefaultLayout>

    );
};

export default OrderManagement;