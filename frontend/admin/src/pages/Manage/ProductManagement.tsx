import React from "react";
import DefaultLayout from "../../layout/DefaultLayout";
import TableThree from "../../components/Tables/TableThree";

const ProductManagement: React.FC = () => {
    return (
        <DefaultLayout>
            <div className="flex flex-col gap-10">
                <TableThree />
            </div>
        </DefaultLayout>
    );
};

export default ProductManagement;