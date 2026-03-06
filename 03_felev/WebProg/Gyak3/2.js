const ul = document.querySelector("ul")

ul.addEventListener("click", function(e) {
    if (!e.target.matches("li")) return;
    e.target.style.color = "red"
    e.target.style.fontWeight = "bold"
})