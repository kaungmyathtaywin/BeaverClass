require("dotenv").config()
console.log(process.env)
const express = require("express")

// const bodyParser = require("body-parser")
const cors = require("cors")
const app = express()

const cart = require("./api/cart")
const classes = require("./api/classes")
const draft = require("./api/draft")

app.use(cors())
app.use(express.json())
app.use(express.urlencoded({ extended: true }))

app.get("/", (req, res) => {
  return res.json({ message: "Hello World" })
})

app.get("/class", (req, res) => {})

// after this line need to use middleware to check if user is logged in
app.use((req, res, next) => {
  console.log("Middleware")
  // attach user info to req

  next()
})
app.get("/api/classes/:class_id", (req, res) => {})

app.get("/api/cart", (req, res) => {
  res.json({})
})
app.post("/api/cart", (req, res) => {
  res.json({})
})
app.delete("/api/cart", (req, res) => {
  res.json({})
})

app.get("/api/draft", (req, res) => {
  res.json({})
})
app.post("/api/draft", draft.draft)

app.get("/api/user", (req, res) => {
  res.json({})
})

app.post("/api/setting", (req, res) => {
  res.json({})
})
app.get("/api/setting", (req, res) => {
  res.json({})
})

app.listen(3000, () => {
  console.log("Server is running on port 3000.")
})
