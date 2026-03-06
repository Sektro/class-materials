const solution = [
  [".", ".", "F", "."],
  [".", ".", "S", "."],
  ["S", "F", ".", "F"],
  [".", ".", ".", "S"],
];

const puzzle1 = [
  [".", ".", "F", " "],
  [" ", " ", "S", "."],
  ["S", "F", ".", "F"],
  [".", ".", ".", "S"],
];

// 1. A megoldásban van-e fa az 1. sorban?
// 2. Hány sátor van a 3. sorban?
// 3. Állítsd elő a csak fás puzzle-t
// 4. Minden mező ki van-e töltve?
// [removed] 5. Soronként a sátrak száma
// [removed] 6. Minden fa mellett 4 irányban 1 sátor áll-e?

const task1 = solution[0].some((e) => e === "F");
const task2 = solution[2].filter((e) => e === "S").length;
const task3 = solution.map((row) => row.map((e) => (e === "F" ? "F" : " ")));
const task4 = puzzle1.every((row) => row.every((e) => e !== " "));
/* const task5 = solution.map((row) => row.filter((e) => e === "S").length);
const task6 = puzzle1.every((row, i) =>
  row.every((e, j) =>
    e === "F"
      ? [puzzle1[i][j - 1], puzzle1[i][j + 1], puzzle1[i - 1]?.[j], puzzle1[i + 1]?.[j]].filter(
          (e) => e === "S"
        ).length === 1
      : true
  )
); */
console.log(task1);
console.log(task2);
console.log(task3);
console.log(task4);
/* console.log(task5);
console.log(task6); */
