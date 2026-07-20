import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import Home from "./pages/Home";
import App from "./App";
import User from "./pages/User";
import Order from "./pages/Order";
import Payment from "./pages/Payment";
import Product from "./pages/Product";
import Inventory from "./pages/Inventory";
import Notification from "./pages/Notification";

const appRoutes = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<App />}>
        <Route index element={<Home />} />
        <Route path="/user" element={<User />} />
        <Route path="/order" element={<Order />} />
        <Route path="/payment" element={<Payment />} />
        <Route path="/product" element={<Product />} />
        <Route path="/inventory" element={<Inventory />} />
        <Route path="/notification" element={<Notification />} />
      </Route>
    </>,
  ),
);

export default appRoutes;
