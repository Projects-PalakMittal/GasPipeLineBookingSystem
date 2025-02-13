require("dotenv").config();
const express = require("express");
const { createProxyMiddleware } = require("http-proxy-middleware");

const app = express();
app.use(express.json());

const SERVICES = {
    auth: "http://localhost:6969",   // Spring Boot (JWT Creation)
    vendor: "http://localhost:6969", // Spring Boot (Vendor API)
    customer: "http://localhost:4000" // Express.js (Customer API)
};

// Route Authentication Requests to Spring Boot
app.use("/user", createProxyMiddleware({ target: SERVICES.auth, changeOrigin: true }));

// Route Vendor Requests to Spring Boot
app.use("/vendor", createProxyMiddleware({ target: SERVICES.vendor, changeOrigin: true }));

// Route Customer Requests to Express.js
app.use("/customer", createProxyMiddleware({ target: SERVICES.customer, changeOrigin: true }));

app.listen(7979, () => console.log("API Gateway running on port 7979"));
