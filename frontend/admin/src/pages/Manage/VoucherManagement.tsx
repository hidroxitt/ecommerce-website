import React from "react";
import DefaultLayout from "../../layout/DefaultLayout";
import TableVoucher from "../../components/Tables/TableVoucher";
import VoucherModal from "../Modal/VoucherModal";

const WarehouseManagement: React.FC = () => {
    return (
        <DefaultLayout>
            <div className="flex flex-col gap-10">
                <VoucherModal />
                <TableVoucher />
            </div>
        </DefaultLayout>
    );
};

export default WarehouseManagement;