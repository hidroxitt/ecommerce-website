import React from "react";
import DefaultLayout from "../../layout/DefaultLayout";
import TableUser from "../../components/Tables/TableUser";
import { Link } from "react-router-dom";

const UserManagement: React.FC = () => {
    return (
        <DefaultLayout>
            <div className="flex flex-col gap-10">
                <TableUser />
            </div>
        </DefaultLayout>
    );
};

export default UserManagement;