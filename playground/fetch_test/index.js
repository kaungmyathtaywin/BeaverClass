const axios = require("axios")

const BASE_URL = "https://classes.oregonstate.edu/api/"

const queries = ["page=fose", "route=search", "alias=CS*"]

const request = axios.create({
  baseURL: BASE_URL,
})

const main = async () => {
  const config = {
    method: "post",
    url: "https://classes.oregonstate.edu/api/?page=fose&route=search&alias=CS*",
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify({
      other: { srcdb: "999999" },
      criteria: [{ field: "alias", value: "CS*" }],
    }),
  }

  axios(config)
    .then(function (response) {
      console.log(JSON.stringify(response.data))
    })
    .catch(function (error) {
      console.error(error)
    })
}

main()
