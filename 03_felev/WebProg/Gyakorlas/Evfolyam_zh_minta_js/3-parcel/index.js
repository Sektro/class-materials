const canvas = document.querySelector('canvas');
const ctx = canvas.getContext("2d");

// Application state
const plane = {
  x: 0,
  y: 20,
  width: 60,
  height: 30,
  vx: 0,
  img: new Image(),
};
const parcel = {
  x: 0,
  y: plane.y + plane.height,
  width: 30,
  height: 30,
  vx: 0,
  vy: 0,
  ay: 0,
  img: new Image(),
};
const house = {
  x: 400,
  y: canvas.height - 120,
  width: 100,
  height: 100,
  img: new Image(),
};
let gameState = 0; // 0-start, 1-moving, 2-dropping, 3-hit, 4-missed

// ================= Game loop =====================

// Time-based animation (from the lecture slide)
let lastFrameTime = performance.now();

function next(currentTime = performance.now()) {
  const dt = (currentTime - lastFrameTime) / 1000; // seconds
  lastFrameTime = currentTime;

  update(dt); // Update current state
  render(); // Rerender the frame

  requestAnimationFrame(next);
}

win = false;
lose = false;
function update(dt) {
  plane.x = plane.x + plane.vx;
  parcel.x = parcel.x + parcel.vx;
  parcel.y = parcel.y + parcel.vy;
  if (isCollision(parcel,house) && !win) {
    console.log('Package Delivered!');
    win = true;
    plane.vx = 0;
    parcel.vx = 0;
    parcel.vy = 0;
  }
  if (parcel.y >= canvas.height) {
    console.log('Elbasztad!');
    lose = true;
    plane.vx = 0;
    parcel.vx = 0;
    parcel.vy = 0;
  }
  render();
}

function render() {
  ctx.clearRect(0, 0, canvas.width, canvas.height)
  ctx.drawImage(planeImage, plane.x, plane.y, plane.width, plane.height);
  ctx.drawImage(parcelImage, parcel.x, parcel.y, parcel.width, parcel.height);
  ctx.drawImage(houseImage, house.x, house.y, house.width, house.height);
  if (win) {
    ctx.fillStyle = 'black';
    ctx.font = '30px Arial';
    ctx.fillText("Delivered!", 0, 100);
  }
  else if (lose) {
    ctx.fillStyle = 'black';
    ctx.font = '30px Arial';
    ctx.fillText("Elbasztad!", 0, 100);
  }
}
const planeImage = new Image();
planeImage.src = 'plane.png';
const parcelImage = new Image();
parcelImage.src = 'parcel.png';
const houseImage = new Image();
houseImage.src = 'house.png';
planeImage.addEventListener('load', () => {
  ctx.drawImage(planeImage, plane.x, plane.y, plane.width, plane.height);
});
parcelImage.addEventListener('load', () => {
  ctx.drawImage(parcelImage, parcel.x, parcel.y, parcel.width, parcel.height);
});
houseImage.addEventListener('load', () => {
  ctx.drawImage(houseImage, house.x, house.y, house.width, house.height);
});



// Start the loop
plane.img.src = "plane.png";
house.img.src = "house.png";
parcel.img.src = "parcel.png";
next(); 

// =============== Utility functions =================

function isCollision(box1, box2) {
  return !(
    box2.y + box2.height < box1.y ||
    box1.x + box1.width < box2.x ||
    box1.y + box1.height < box2.y ||
    box2.x + box2.width < box1.x
  );
}

firstClick = true;
canvas.addEventListener('click', function(e) {
    if (firstClick) {
      plane.vx = 2;
      parcel.vx = plane.vx;
      firstClick = false;
    }
    else {
      parcel.vy = 5;
    }
})


setInterval(update, 1000);


