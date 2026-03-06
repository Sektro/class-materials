const canvas = document.querySelector("canvas")
const ctx = canvas.getContext("2d")

const balls = [
    {
        x: 60,
        y: 100,
        vy: 0,
        r: 20,
        c: 'red'
    }
]

// 1. rajzold ki a labdát
// 2. essen le a labda, gyorsulva
// 3. a canvas alján pattanjon vissza
// 4. ha kattintok a canvasen spawnoljon adott helyre új labda random sugárral és szinnel

//1.
/*
ctx.beginPath()
ctx.arc(balls[0].x, balls[0].y, balls[0].r, 0, 2 * Math.PI)
ctx.strokeStyle = balls[0].c
ctx.fillStyle = balls[0].c
ctx.fill()
*/

//2.
function render() {
    ctx.clearRect(0, 0, canvas.width, canvas.height)
    for (const ball of balls) {
        ctx.beginPath()
        ctx.arc(ball.x, ball.y, ball.r, 0, 2 * Math.PI)
        ctx.strokeStyle = ball.c
        ctx.fillStyle = ball.c
        ctx.fill()
    }
}
function update(dt) {
    for (const ball of balls) {
        ball.vy += dt * 0.0005
        ball.y += dt * ball.vy
        if (ball.y >= canvas.height - ball.r && ball.vy > 0) {
            ball.vy *= -1
        }
    }
}
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

//4.
canvas.addEventListener('click', function(e) {
    balls.push ({
        x: e.clientX,
        y: e.clientY,
        vy: 0,
        r: Math.floor(Math.random() * 20 + 5),
        c: `rgb(${Math.random() * 255}, ${Math.random() * 255}, ${Math.random() * 255})`
    })
    ctx.beginPath()
    ctx.arc(e.screenX, e.screenY, 20, 0, 2 * Math.PI)
    ctx.fill()
})