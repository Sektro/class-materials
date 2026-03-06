//const matrix = [...Array(5).fill([...Array(5).fill(0)])]
const table = document.querySelector("table")
const colorPicker = document.querySelector("input")
/*
for (const row of matrix) {
    let tr = document.createElement("tr")
    for (const cell of row) {
        let td = document.createElement("td")
        td.innerHTML = cell
        tr.appendChild(td)
    }
    table.appendChild(tr)
}
*/
/*
table.innerHTML = 
matrix.map(row => `<tr>${
    row.map(cell => `<td>${cell}</td>`).join("")
}</tr>`).join("")
*/

table.innerHTML = 
[...Array(20).fill(0).keys()].map(row => `<tr>${
    [...Array(40).fill(0).keys()].map(cell => `<td></td>`).join("")
}</tr>`).join("")


table.addEventListener("click", function(e) {
    if (e.target.matches("td")) {
        e.target.style.backgroundColor = colorPicker.value
    }
})

table.addEventListener("mousemove", function(e) {
    if (e.target.matches("td")) {
        if (e.buttons === 1) {
            e.target.style.backgroundColor = colorPicker.value
        }
    }
})