// AJAX 

const input = document.querySelector("input")
const tbody = document.querySelector("tbody")

async function updateTable() { 
    const resp = await fetch('cats.php?filter=' + input.value)
    const data = await resp.json()

    tbody.innerHTML = data.map(
        c => `<tr><td>${c.id}</td><td>${c.name}</td><td>${c.color}</td><td>${c.age}</td></tr>`
    ).join("")
}

updateTable()
input.addEventListener('input',updateTable)