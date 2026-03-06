// Math.random() --> 0 és 1 között egy szám
// Math.floor() --> lefele kerekít
// Math.ceil() --> felfele kerekít

const target = Math.ceil(Math.random() * 100)

const input = document.querySelector("input")
const button = document.querySelector("button")
const span = document.querySelector("span")


button.addEventListener("click", function() {
    const n = input.valueAsNumber //vagy +input.value
    if (!isNaN(n)) {
        if (n > target)
            //console.log("kisebb")
            span.innerText = "kisebb"
        else if (n < target)
            //console.log("nagyobb")
            span.innerText = "nagyobb"
        else
            //console.log("eltalaltad")
            span.innerText = "eltalaltad"
    } else span.innerText = "NEM SZAM!!!"
}) 