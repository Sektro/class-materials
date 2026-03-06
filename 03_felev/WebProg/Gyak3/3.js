const matrix  = [
    ["alma", "körte", "banán"],
    ["füge", "dinnye", "eper"],
    ["málna", "datolya", "grapefruit"]
]

//magyarázat: 1:33:45
function delegate(parent, type, selector, handler) {
    parent.addEventListener(type, function (event) {
      const targetElement = event.target.closest(selector);
  
      if (this.contains(targetElement)) {
        handler.call(targetElement, event);
      }
    });
  }
const table = document.querySelector("table")

table.innerHTML = 
matrix.map(row => `<tr>${
    row.map(cell => `<td>${cell}</td>`).join("")
}</tr>`).join("")
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

delegate(table, "mouseover", "tr", function(e) {
    this.style.backgroundColor = "yellow" //this = tr
    //e.targetElement.style.backgroundColor = "yellow" 2.megoldás
    //^ez "arrow function"-nél hasznos: funcion(e) helyett (e) =>
})

delegate(table, "mouseout", "tr", function(e) {
    this.style.backgroundColor = "" 
})