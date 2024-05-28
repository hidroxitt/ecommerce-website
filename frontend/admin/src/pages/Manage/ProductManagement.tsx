import React from "react";
import DefaultLayout from "../../layout/DefaultLayout";
import TableProduct from "../../components/Tables/TableProduct";
import { Link } from "react-router-dom";

const ProductManagement: React.FC = () => {
    return (
        <DefaultLayout>
            <div className="flex flex-col gap-10">
                <Link
                    to="#"
                    className="inline-flex items-center justify-center gap-2.5 rounded-md bg-primary py-3 px-2 text-center font-medium text-white hover:bg-opacity-90 w-full lg:w-1/2 xl:w-1/5"
                >
                    <span>
                        <i className="fa-sharp fa-solid fa-plus"></i>
                    </span>
                    Thêm sản phẩm
                </Link>
                <TableProduct />
            </div>
        </DefaultLayout>
    );
};

export default ProductManagement;