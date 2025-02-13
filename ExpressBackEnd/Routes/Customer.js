const express = require('express')
const db = require('../db')
const bcrypt = require("bcrypt")
const verifyCustomerToken = require("../authMiddleware");

const router = express.Router()

//get all vendor which are located in customer address;
router.get('/all-vendor', verifyCustomerToken, async (request, response) => {
    try {
        const customer_id = request.query.customer_id;

        if (!customer_id) {
            return response.status(400).json({ error: "customer_id is required" });
        }

        // Get the customer's address_id
        const query1 = `SELECT address_id FROM user WHERE id = ?`;
        const [customerRows] = await db.pool.execute(query1, [customer_id]);

        if (customerRows.length === 0) {
            return response.status(404).json({ error: "Customer not found" });
        }

        const address_id = customerRows[0].address_id;

        // Get the city where the customer is located
        const query2 = `SELECT city FROM address WHERE id = ?`;
        const [addressRows] = await db.pool.execute(query2, [address_id]);

        if (addressRows.length === 0) {
            return response.status(404).json({ error: "Address not found" });
        }

        const city = addressRows[0].city;

        // Get all vendors in the same city
        const query3 = `SELECT id, first_name, last_name FROM user WHERE address_id IN (SELECT id FROM address WHERE city = ?)`;
        const [vendorRows] = await db.pool.execute(query3, [city]);

        if (vendorRows.length === 0) {
            return response.status(404).json({ message: "No vendors found in this city" });
        }

        response.json({ vendors: vendorRows });

    } catch (error) {
        console.error(error);
        response.status(500).json({ error: "Internal server error" });
    }
});

// get all subscription of vendor that has been selected
router.get('/all-subscription', verifyCustomerToken, async (request, response) => {
    try {
        const vendor_id = request.query.vendor_id;

        if (!vendor_id) {
            return response.status(400).json({ error: "Please select the vendor" });
        }

        const query = `SELECT * FROM subscription WHERE vendor_id = ?`;
        const [subscriptionRows] = await db.pool.execute(query, [vendor_id]);

        if (subscriptionRows.length === 0) {
            return response.status(404).json({ message: "No subscriptions found for this vendor" });
        }

        response.json({ subscriptions: subscriptionRows });

    } catch (error) {
        console.error(error);
        response.status(500).json({ error: "Internal server error" });
    }
});

//updating customer profile
router.put('/profile', verifyCustomerToken, async (request, response) => {
    try {
        const { first_name, last_name, phone_no, password, address, city, state, pincode } = request.body;

        // Hash password before saving
        const hashedPassword = await bcrypt.hash(password, 10); 

        // Update User table
        const statement1 = `
            UPDATE User
            SET first_name = ?, last_name = ?, phone_no = ?, password = ?
            WHERE id = ?
        `;

        await db.pool.execute(statement1, [
            first_name, last_name, phone_no, hashedPassword, request.user['id']
        ]);

        // Get address_id
        const statement2 = `SELECT address_id FROM User WHERE id = ?`;
        const [rows] = await db.pool.execute(statement2, [request.user['id']]);

        if (rows.length === 0) {
            return response.status(404).json({ error: "User not found" });
        }

        const address_id = rows[0].address_id;

        // Update Address table
        const statement3 = `
            UPDATE Address
            SET address = ?, city = ?, state = ?, pincode = ?
            WHERE id = ?
        `;

        await db.pool.execute(statement3, [address, city, state, pincode, address_id]);

        response.json({ message: "Profile updated successfully" });

    } catch (error) {
        console.error(error);
        response.status(500).json({ error: "Internal server error" });
    }
});

//get customer details for profile
router.get('/profile', verifyCustomerToken, async (request, response) => {
    try {
        const statement = `
            SELECT user_name, first_name, last_name, email, phone_no, aadhar_card, pan_card, password
            FROM user
            WHERE id = ?
        `;

        const [users] = await db.pool.execute(statement, [request.user['id']]);

        if (users.length === 0) {
            return response.status(404).json({ error: "User does not exist" });
        }

        response.json({ success: true, profile: users[0] });

    } catch (error) {
        console.error(error);
        response.status(500).json({ error: "Internal server error" });
    }
});


module.exports = router