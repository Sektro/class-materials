// 1. kirajzolni
// 2. léggömb mozgása
// 3. eseményre rakéta mozgása
// 4. ütközésvizsgálat
// 5. vége
// 6. pontszámítás - talán nem kell
// 7. újrakezdés - nem kell

const canvas = document.querySelector("canvas");
const ctx = canvas.getContext("2d");

const width = canvas.width;
const height = canvas.height;

const asteroid = {
  x: 0,
  y: 20,
  width: 50,
  height: 40,
  img: new Image(),
  vx: 60, // px/s
};
const rocket = {
  x: width / 2,
  y: height - 50,
  width: 20,
  height: 50,
  img: new Image(),
  vy: 0,
};
asteroid.img.src = "asteroid.png";
rocket.img.src = "rocket.png";

let isEnd = 0; // 0 - játék, 1 - nyert, 2 - veszített
let point = 0;

// ============= Előadásból kimásolva =================

let lastFrameTime = performance.now();

function next(currentTime = performance.now()) {
  const dt = (currentTime - lastFrameTime) / 1000; // seconds
  lastFrameTime = currentTime;

  update(dt); // Update current state
  render(); // Rerender the frame

  if (!isEnd) requestAnimationFrame(next);
}

function update(dt) {
  asteroid.x += asteroid.vx * dt;
  rocket.y += rocket.vy * dt;

  if (isCollision(asteroid, rocket)) {
    isEnd = 1;
    point = calculateScore()
  }
  if (!isEnd && rocket.y + rocket.height < 0) {
    isEnd = 2;
  }
  if (asteroid.y > width) {
    isEnd = 2;
  }
}

function render() {
  // ctx.fillStyle = "lightblue";
  // ctx.fillRect(0, 0, width, height);
  ctx.clearRect(0, 0, width, height);

  ctx.drawImage(asteroid.img, asteroid.x, asteroid.y, asteroid.width, asteroid.height);

  ctx.drawImage(rocket.img, rocket.x, rocket.y, rocket.width, rocket.height);

  ctx.fillStyle = "#66CCFF";
  ctx.font = "50px Helvetica";
  if (isEnd === 1) {
    ctx.fillText(`Won: ${point} pts`, 50, 200);
  }
  if (isEnd === 2) {
    ctx.fillText("Lost", 50, 200);
  }
}

next(); // Start the loop

// setTimeout(() => render(), 1000);
// render();

// =============== Eseménykezelők ==================

document.addEventListener("keydown", (e) => {
  rocket.vy = -200;
});
canvas.addEventListener("click", (_) => {
  isEnd = 0;
  asteroid.x = 0;
  rocket.y = height - 50;
  rocket.vy = 0;
  lastFrameTime = performance.now();
  next();
});

// =============== Segédfüggvények =================

function isCollision(box1, box2) {
  return !(
    box2.y + box2.height < box1.y ||
    box1.x + box1.width < box2.x ||
    box1.y + box1.height < box2.y ||
    box2.x + box2.width < box1.x
  );
}

function calculateScore(){
  return Math.round(100 * Math.abs(asteroid.x + asteroid.width / 2 - rocket.x - rocket.width / 2));
}