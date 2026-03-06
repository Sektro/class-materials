const input = document.querySelector("input")
const button = document.querySelector("button")
const table = document.querySelector("table")

//[...Array(input.valueAsNumber).fill(0).keys()]

button.addEventListener("click", function() {
    table.innerHTML = ""
    const n = input.valueAsNumber
    /*
    for (let i = 1; i <= n; i++) {
        let tr = document.createElement("tr")
        for (let j = 1; j <= n; j++) {
            let td = document.createElement("td")
            td.innerText = i * j
            tr.appendChild(td)
        }
        table.appendChild(tr)
    }
    */
    const nums = [...Array(n).fill(0).keys()].map(x => x + 1)

    table.innerHTML = 
    nums.map(x => `<tr>${
        nums.map(y => `<td>${x*y}</td>`).join("")
    }</tr>`).join("")
})