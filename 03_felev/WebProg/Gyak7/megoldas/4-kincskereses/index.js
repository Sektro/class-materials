// 1. Kattintés távolság
// 2. Színezés
// 3. Számolás
// 4. Találás
// 5. Dupla Kattintés
// 6. Generálás - nem kell

function random(a, b) {
  return Math.floor(Math.random() * (b - a + 1)) + a;
}
function xyCoord(td) {
  return {
    x: td.cellIndex,
    y: td.parentNode.rowIndex,
  };
}
function distanceHue(height, width, x1, y1, x2, y2) {
  const distance = Math.sqrt((x1 - x2) ** 2 + (y1 - y2) ** 2);
  const maxDistance = Math.sqrt((height - 1) ** 2 + (width - 1) ** 2);
  console.log(distance / maxDistance);
  const hue = (120 * distance) / maxDistance;
  return hue;
}

const containerDiv = document.querySelector("#container");
const counterSpan = document.querySelector("#span-counter");
const widthInput = document.querySelector("#input-width");
const heightInput = document.querySelector("#input-height");
const generateForm = document.querySelector("#form-generate");

let width = 14;
let height = 10;

// ========== Eddig a keret ==============

let treasure = {
  x: random(0, 13),
  y: random(0, 9)
};
let count = 0;

containerDiv.addEventListener("click", function (e) {
  if (e.target.matches("td") && !e.target.dataset.clicked) {
    const td = e.target;
    const { x, y } = xyCoord(td);
    const h = distanceHue(height, width, treasure.x, treasure.y, x, y);
    td.style.backgroundColor = `hsla(${h}, 100%, 50%, .7)`;

    td.dataset.clicked = true;
    count++;
    counterSpan.innerHTML = count;

    if (treasure.x === x && treasure.y === y) {
      td.classList.add("treasure");
    }
  }
});