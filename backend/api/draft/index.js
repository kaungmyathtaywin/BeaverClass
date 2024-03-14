const { generateDraft } = require("./genText")

const controller = {
  draft: async (req, res) => {
    try {
      const { name, professorName, className, classId, email } = req.body
      const text = await generateDraft({ name, professorName, className, classId, email })
      res.json({ text })
    } catch (error) {
      res.status(404).json({ message: error.message })
    }
  },
}

module.exports = controller
