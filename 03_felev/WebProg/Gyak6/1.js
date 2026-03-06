const canvas = document.querySelector("canvas")
const ctx = canvas.getContext("2d")

// 1. direkt rajzolás
ctx.fillStyle = 'green'
ctx.fillRect(100, 100, 50, 30)
ctx.strokeStyle = 'blue'
ctx.strokeRect(120, 120, 50, 30)

ctx.font = '30px Arial'
ctx.fillText('ok', 200, 200)
ctx.strokeText('nah man', 220, 220)

const honoredOne = new Image()
honoredOne.src = 'Satorugojo.webp'

honoredOne.addEventListener('load', () => {
    ctx.drawImage(honoredOne, 250, 250)
})

// 2. indirekt rajzolás
ctx.beginPath()
ctx.moveTo(50, 50)
ctx.lineTo(50, 100)
ctx.lineTo(100, 100)
ctx.lineTo(50, 50)
ctx.stroke()
ctx.fill()

ctx.beginPath()
ctx.arc(180, 80, 20, 0, 2 * Math.PI)
ctx.fill()