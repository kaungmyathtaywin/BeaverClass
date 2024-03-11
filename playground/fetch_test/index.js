const axios = require("axios")
const cron = require("node-cron")
const moment = require("moment")
require("moment-timezone")

const BASE_URL = "https://classes.oregonstate.edu/api/"

const queries = ["page=fose", "route=search", "alias=CS*"]

const prevPrev = {
  cs599: 0,
}

// temp
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

const cs599 = {
  group: "code:CS 599",
  key: "crn:59371",
  srcdb: "202403",
  matched: "crn:59371",
}

const urlFetch = "https://classes.oregonstate.edu/api/?page=fose&route=details"

const token = ""
const notify_req = axios.create({
  baseURL: `https://notify-api.line.me/api/notify`,
  headers: {
    Authorization: `Bearer ${token}`,
    [`Content-Type`]: `application/x-www-form-urlencoded`,
  },
})

const notify = async message => {
  const msg = `message=${message}
  https://classes.oregonstate.edu/?keyword=cs599&srcdb=999999`
  await notify_req.post("/", msg)
}

const main2 = async () => {
  const config = {
    method: "post",
    url: urlFetch,
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify(cs599),
  }
  try {
    const response = await axios(config)
    const {
      max_enroll, // max people in the class
      enrollment, // enronment number
      waitlist_capacity, // max waitlist capacity
      ssbsect_wait_count, // people are waiting in the class
      ssbsect_wait_avail, // available to join waitlist
    } = response.data
    const numberAvailabelForWaitlist = +ssbsect_wait_avail
    let formattedMessage = `class "cs599 cloud development" \n`
    let notifyIncrease = false
    if (prevPrev.cs599 !== +enrollment) {
      notifyIncrease = true
      formattedMessage =
        formattedMessage + `num's enroll increases ${prevPrev.cs599} -> ${enrollment}\n`
      prevPrev.cs599 = +enrollment
    }
    const time = moment().tz("America/Los_Angeles").format("YYYY-MM-DD HH:mm:ss")
	  console.log("last run at:", time)

    formattedMessage =
      time +
      "\n" +
      formattedMessage +
      ` people ${enrollment}/${max_enroll}
  available to join waitlist ${numberAvailabelForWaitlist}
  waitlist capacity: ${ssbsect_wait_count}/${waitlist_capacity}`
    if (notifyIncrease || +max_enroll - +enrollment > 0 || numberAvailabelForWaitlist > 0) {
      await notify(formattedMessage)
    }
  } catch (error) {
    const time = moment().tz("America/Los_Angeles").format("YYYY-MM-DD HH:mm:ss")
	  console.log("last run error at:", time)
    console.error(error)
  }
}

cron.schedule("*/1 * * * *", async () => {
  await main2()
})

// main2()