const input = document.querySelector("input")
//const body = document.querySelector("body")

input.addEventListener("input", function() {
    document.body.style.backgroundColor = input.value
})

//lehet change is a trigger