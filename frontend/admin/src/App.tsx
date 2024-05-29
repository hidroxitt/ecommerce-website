import { useEffect, useState } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';

import Loader from './common/Loader';
import PageTitle from './components/PageTitle';
import SignIn from './pages/Authentication/SignIn';
import SignUp from './pages/Authentication/SignUp';
import Chart from './pages/Chart';
import ECommerce from './pages/Dashboard/ECommerce';
import Settings from './pages/Settings';
import Tables from './pages/Tables';
import Alerts from './pages/UiElements/Alerts';
import RevenueManagement from './pages/Manage/RevenueManagement';
import ProductManagement from './pages/Manage/ProductManagement';
import VoucherManagement from './pages/Manage/VoucherManagement';
import UserManagement from './pages/Manage/UserManagement';
import WarehouseManagement from './pages/Manage/WarehouseManagement';
import OrderManagement from './pages/Manage/OrderManagement';

function App() {
  const [loading, setLoading] = useState<boolean>(true);
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  useEffect(() => {
    setTimeout(() => setLoading(false), 1000);
  }, []);

  return loading ? (
    <Loader />
  ) : (
    <>
      <Routes>
        <Route
          index
          element={
            <>
              <PageTitle title="eCommerce Dashboard | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <ECommerce />
            </>
          }
        />
        <Route
          path="/tables"
          element={
            <>
              <PageTitle title="Tables | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <Tables />
            </>
          }
        />
        <Route
          path="/settings"
          element={
            <>
              <PageTitle title="Settings | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <Settings />
            </>
          }
        />
        <Route
          path="/chart"
          element={
            <>
              <PageTitle title="Basic Chart | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <Chart />
            </>
          }
        />
        <Route
          path="/ui/alerts"
          element={
            <>
              <PageTitle title="Alerts | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <Alerts />
            </>
          }
        />
        <Route
          path="/auth/signin"
          element={
            <>
              <PageTitle title="Signin | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <SignIn />
            </>
          }
        />
        <Route
          path="/auth/signup"
          element={
            <>
              <PageTitle title="Signup | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <SignUp />
            </>
          }
        />
        <Route
          path="/manage/revenue-management"
          element={
            <>
              <PageTitle title="Revenue Management | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <RevenueManagement />
            </>
          }
        />
        <Route
          path="/manage/product-management"
          element={
            <>
              <PageTitle title="Product Management | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <ProductManagement />
            </>
          }
        />
        <Route
          path="/manage/voucher-management"
          element={
            <>
              <PageTitle title="Voucher Management | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <VoucherManagement />
            </>
          }
        />
        <Route
          path="/manage/user-management"
          element={
            <>
              <PageTitle title="User Management | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <UserManagement />
            </>
          }
        />
        <Route
          path="/manage/warehouse-management"
          element={
            <>
              <PageTitle title="Warehouse Management | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <WarehouseManagement />
            </>
          }
        />
        <Route
          path="/manage/order-management"
          element={
            <>
              <PageTitle title="Order Management | TailAdmin - Tailwind CSS Admin Dashboard Template" />
              <OrderManagement />
            </>
          }
        />
      </Routes>
    </>
  );
}

export default App;
