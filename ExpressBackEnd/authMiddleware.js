const jwt = require("jsonwebtoken");
require("dotenv").config();
const {SECRET_KEY} = require('./config')

const verifyCustomerToken = (req, res, next) => {
    const authHeader = req.headers["authorization"];
    if (!authHeader || !authHeader.startsWith("Bearer ")) {
        return res.status(403).json({ message: "Unauthorized" });
    }

    const token = authHeader.split(" ")[1];

    try {
        const decoded = jwt.verify(token, SECRET_KEY);
        if (decoded.role !== "CUSTOMER") {
            return res.status(403).json({ message: "Access denied" });
        }
        req.user = decoded;
        next();
    } catch (error) {
        return res.status(401).json({ message: "Invalid token" });
    }
};

module.exports = verifyCustomerToken;
