const h1 = document.querySelector("h1") //id szerint: #idnev | class szerint: .classnev
h1.innerText = "megváltozott a <i>cím</i>"
h1.innerHTML = "megváltozott a <i>cím2</i>"

h1.style.backgroundColor = "green"
h1.style.color = "rgb(0,0,255)"

const p = document.querySelector("p") //az első p taget állítja
p.style.fontWeight = "bold" 

const p2 = document.querySelectorAll("p")[1] //egy tetszőleges elem
p.style.color = "cyan"

//összes elem:
const pall = document.querySelectorAll("p") 
for (const panic of pall)
    panic.style.backgroundColor = "blue"
//for (let i = 0; i < pall.length; i++)
//    {}

const img = document.querySelector("img")
img.src = "https://images4.alphacoders.com/133/1332281.jpeg"
img.width = 300