import React from "react";

export default function User() {
  return (
    <div className="bg-gray-300 h-screen grid-cols-3 p-5">
      <button className="bg-blue-400 w-40 rounded-xl text-white">
        Verify OTP
      </button>
      <button className="bg-blue-400 w-40 rounded-xl text-white">SignUp</button>
      <button className="bg-blue-400 w-40 rounded-xl text-white">Login</button>
      <button className="bg-blue-400 w-40 rounded-xl text-white">
        Get All
      </button>
      <button className="bg-blue-400 w-40 rounded-xl text-white">
        Update User
      </button>
      <button className="bg-blue-400 w-40 rounded-xl text-white">
        Delete User
      </button>
      <button className="bg-blue-400 w-40 rounded-xl text-white">
        Update Password
      </button>
      <button className="bg-blue-400 w-40 rounded-xl text-white">Send OTP</button>
      <button className="bg-blue-400 w-40 rounded-xl text-white">Forget Password</button>
      <button className="bg-blue-400 w-40 rounded-xl text-white"></button>
      <button className="bg-blue-400 w-40 rounded-xl text-white"></button>
      <button className="bg-blue-400 w-40 rounded-xl text-white"></button>
    </div>
  );
}
