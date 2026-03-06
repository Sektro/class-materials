const span = document.querySelector("span")
let lastClick = null

window.addEventListener("click", function(e) {
    //console.log(e)
    if (lastClick === null) {
            lastClick = e
            span.innerText = "Várom a második kattintást"
    } else {
        const dt = e.timeStamp - lastClick.timeStamp
        const ds = ((e.screenX - lastClick.screenX)**2 + (e.screenY - lastClick.screenY)**2)**0.5
        span.innerText = `Eltelt idő: ${dt} ms\nElmozdulás: ${ds} px`
        lastClick = e
    }
})