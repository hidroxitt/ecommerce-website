import React from "react";
import DefaultLayout from "../../layout/DefaultLayout";
import TableWarehouse from "../../components/Tables/TableWarehouse";

const WarehouseManagement: React.FC = () => {
    return (
        <DefaultLayout>
            <div className="flex flex-col gap-10">
                <TableWarehouse />
            </div>
        </DefaultLayout>
    );
};

export default WarehouseManagement;