import React from "react";
import ChartOne from '../../components/Charts/ChartOne';
import ChartThree from '../../components/Charts/ChartThree';
import TableOne from '../../components/Tables/TableOne';
import DefaultLayout from '../../layout/DefaultLayout';

const RevenueManagement: React.FC = () => {
    return (
        <DefaultLayout>
            <div className="mt-4 grid grid-cols-12 gap-4 md:mt-6 md:gap-6 2xl:mt-7.5 2xl:gap-7.5">
                <ChartOne />
                <ChartThree />
                <div className="col-span-12">
                    <TableOne />
                </div>
            </div>
        </DefaultLayout>
    );
};

export default RevenueManagement;