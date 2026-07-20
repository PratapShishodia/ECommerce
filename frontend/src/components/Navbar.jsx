import React from "react";
import { NavLink } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="bg-cyan-400 text-white h-15 p-5 flex justify-between align-center w-full py-4">
      {/* logo */}
      <h2 className="font-bold text-2xl">ECommerce</h2>
      {/* navLinks */}
      <ul className="text-xl flex justify-between w-200">
        <li>
          <NavLink to="/user">User</NavLink>
        </li>
        <li>
          <NavLink to="/product">Product</NavLink>
        </li>
        <li>
          <NavLink to="/payment">Payment</NavLink>
        </li>
        <li>
          <NavLink to="/order">Order</NavLink>
        </li>
        <li>
          <NavLink to="/notification">Notification</NavLink>
        </li>
        <li>
          <NavLink to="/inventory">Inventory</NavLink>
        </li>
      </ul>
      <div className="flex justify-between gap-6">
        <h3 className="text-xl">
          <NavLink to="#">Profile</NavLink>
        </h3>
        <h3 className="text-xl">
          <NavLink to="#">Login</NavLink>
        </h3>
      </div>
    </nav>
  );
}
