import React from "react";
import DefaultLayout from "../../layout/DefaultLayout";
import TableProduct from "../../components/Tables/TableProduct";
import ProductModal from "../Modal/ProductModal";

const ProductManagement: React.FC = () => {
    return (
        <DefaultLayout>
            <div className="flex flex-col gap-10">
                <ProductModal />
                <TableProduct />
            </div>
        </DefaultLayout>
    );
};

export default ProductManagement;