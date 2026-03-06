// 1. referenciák az elemeket (bemenet, kimenet, trigger)
const span = document.querySelector("span")
const button = document.querySelector("button")

// 2. mi történik?
function handleButtonClick() {
    /*
    let n = +span.innerText
    n = n + 1
    span.innerHTML = n
    */
    // span.innerText = +span.innerText + 1
    // span.innerText = parseInt(span.innerText) + 1
    ++span.innerText
}

//3. mikor történjen?

button.addEventListener("click", handleButtonClick)