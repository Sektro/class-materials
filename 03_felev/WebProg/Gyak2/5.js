const fruits = ["ananász", "banán", "citrom", "görögdinnye"]

const ul = document.querySelector("ul")

/*
for (const fruit of fruits) {
    const li = document.createElement("li")
    li.innerText = fruit
    ul.appendChild(li)
}
    */

ul.innerHTML = fruits.map(fruit => `<i>${fruit}</i>`).join("") // "<i>" + fruit + "</i>"