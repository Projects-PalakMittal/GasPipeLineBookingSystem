const express = require('express')
const db = require('../db')
const bcrypt = require("bcrypt")
const config = require('../config')
const mailer = require('../mailer')
const router = express.Router()

const otpStore = {};

const generateOTP = () => Math.floor(100000 + Math.random() * 900000).toString()

router.post("/verify", async (req, res) => {
    const { email } = req.body
    const statement = `SELECT email FROM user WHERE email=? AND user_status='ACTIVE'`
    
    db.pool.query(statement, [email], (error, user) => {
        if (error) {
            console.log(error)
            return res.status(500).json({ message: "Internal Server Error" })
        }
        
        if (user.length === 0) {
            return res.status(404).json({ message: "Email not found" })
        }
        
        const otp = generateOTP()
        otpStore[email] = otp;
        
        mailer.sendMail({
            from: config.EMAIL_FROM,
            to: email,
            subject: "Reset Password",
            html: `<p>OTP: ${otp}</p>`,
        })
        .then(() => {
            res.json({ message: "OTP sent to your email" })
        })
        .catch((error) => {
            console.log(error);
            res.status(500).json({ message: "Internal Server Error" })
        });
    });
});

router.post("/reset-password", async (req, res) => {
    const { email, otp, password } = req.body
    
    if (!otpStore[email] || otpStore[email] !== otp) {
        return res.status(401).json({ message: "Invalid OTP" });
    }
    
    const statement = `SELECT email FROM user WHERE email=? AND user_status='ACTIVE'`
    
    db.pool.query(statement, [email], async (error, user) => {
        if (error) {
            console.log(error)
            return res.status(500).json({ message: "Internal Server Error" })
        }
        
        if (user.length === 0) {
            return res.status(404).json({ message: "Email not found" })
        }
        
        const hashedPassword = await bcrypt.hash(password, 10)
        const updateStatement = `UPDATE user SET password=? WHERE email=? AND user_status='ACTIVE'`
        
        db.pool.query(updateStatement, [hashedPassword, email], (error) => {
            if (error) {
                console.log(error)
                return res.status(500).json({ message: "Internal Server Error" })
            }
            
            delete otpStore[email]
            res.json({ message: "Password updated successfully" })
        });
    });
});

module.exports = router
