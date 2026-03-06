const canvas = document.querySelector("canvas")
const ctx = canvas.getContext("2d")

let x = 0
let v = 0.05

function render() {
    ctx.clearRect(0, 0, canvas.width, canvas.height)
    ctx.fillRect(x, 200, 20, 20)
}

let done = false

function update(dt) {
    if (!firstUpdate)
        firstUpdate = performance.now()
    v += dt * 0.0005
    x += dt * v
    if (x >= 800  && !done) {
        console.log(performance.now() - firstUpdate)
        done = true
    }
}

let firstUpdate = null
let lastFrame = performance.now()

function gameLoop() {
    const now = performance.now()
    const dt = now - lastFrame
    update(dt)
    render()
    lastFrame = now
    requestAnimationFrame(gameLoop)
}

gameLoop()