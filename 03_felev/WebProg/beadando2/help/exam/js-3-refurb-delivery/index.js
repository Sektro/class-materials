console.log(deliveries);

const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');

canvas.width = 800;
canvas.height = 800;


// Animation loop
let lastFrame = performance.now()
function loop(now) {
    let dt = (now - lastFrame) / 1000;
    lastFrame = now;

    update(dt);
    render();
    requestAnimationFrame(loop);
}
function update(dt) {
  console.log(dt);
  
}
function render() {
  
}

// requestAnimationFrame(loop);  // start the animation loop

