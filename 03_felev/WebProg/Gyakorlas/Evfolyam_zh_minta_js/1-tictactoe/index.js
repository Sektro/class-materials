const task1 = document.querySelector('#task1');
const task2 = document.querySelector('#task2');
const task3 = document.querySelector('#task3');
const task4 = document.querySelector('#task4');

const game = [
  "XXOO",
  "O OX",
  "OOO ",
];

l = game[0].length;
everyLength = game.every(x => x.length === l);
task1.innerHTML = everyLength;



everyXO = game[0].split('').every(x => x === 'O' ||  x === 'X');
task2.innerHTML = everyXO;

sum = 0;
for (let i = 0; i < game.length; ++i) {
  for (let j = 0; j < game[i].length; ++j) {
    if (game[i][j] === 'O' ||  game[i][j] === 'X') {++sum;}
  }
}
task3.innerHTML = sum;

kacsaszar = false;
counter = 0;
for (let i = 0; i < game.length && !kacsaszar; ++i) {
  if (game[i].includes("OOO") || game[i].includes("XXX")) {
    counter = i;
    kacsaszar = true;
  }
}
task4.innerHTML = counter;
