require("dotenv").config();
const express = require("express");
const jwt = require("jsonwebtoken");
const cors = require('cors')

const app = express();

// enable the CORS so that other applications can call the APIs
app.use(cors())

app.use(express.json());

const forgotPassword = require('./Routes/ForgotPassword')
app.use('/forgot-password',forgotPassword)

const customer = require('./Routes/Customer')
app.use('/customer',customer);

app.listen(4000, () => console.log("Customer Service running on port 4000"));
