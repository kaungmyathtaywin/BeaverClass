const { GoogleGenerativeAI } = require("@google/generative-ai")

const GEMINI_TOKEN = process.env.GEMINI_TOKEN

const generateDraft = async ({ name, professorName, className, classId, email }) => {
  const genAI = new GoogleGenerativeAI(GEMINI_TOKEN)
  const model = genAI.getGenerativeModel({ model: "gemini-pro" })
  const prompt = `I would like to generate an email to politely request participation in a class that is fully registered.
  I aim to secure a seat within the class capacity. My name is ${name}, and my email is ${email}.
  I intend to send this email to Professor ${professorName} for the class ${className}, with the class ID ${classId}.`
  const result = await model.generateContent(prompt)
  const response = await result.response
  const text = response.text()
  return text
}

module.exports = { generateDraft }
