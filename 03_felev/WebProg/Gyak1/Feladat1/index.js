let t = [0, 1, -2, 45, 9]

let u = []
for (let i = 0; i < t.length; ++i)
    u[i] = t[i] ** 2

console.log(u)

let w = []
for (let el of t) {
    w[w.length] = el ** 2
}
console.log(u)
// F5 --> Node.js
//CTRL + SHIFT + Ö --> (terminálba: ) node *fájlnév*

// 1. másolás
console.log(t.map(x => x ** 2))

// 2. kiválogatás
console.log(t.filter(x => x % 2 === 0))

// 3. keresés
console.log(t.find(x => x < 0))

// 4. eldöntés - minden elemre
console.log(t.every(x => x % 2 === 0))

// 5. eldöntés
console.log(t.some(x => x % 2 === 0))

let auto = {
    year: 2023,
    model: "Ford T100",
    rip: false, 
    setYear: function (_year) {
        this.year = _year
    }
}
//ha const auto, akkor a benne lévő adatokat attól még lehet változtatni

console.log(auto)
console.log(auto.year)
auto.setYear(2018)
console.log(auto["year"])

function sqr(n) {
    return n ** 2
}

let square = n => n ** 2
console.log(square(4))
square = n => n ** 3
console.log(square(4))

const circle = n => n ** 2
console.log(circle(4))
//circle = n => n ** 3   <--- type error
//console.log(circle(4))


// 1. Feladat - összeadás (for, for-of, reduce)
let q = [5, 6, 1, 0, 1, 2, 3, 4]

let n = 0
for (let i = 0; i < 8; ++i)
    if (q[i] % 2 === 0)
        n = n + q[i]    
console.log("párosok összege: " + n)

n = 0
for (let elem of q)
    if (elem % 2 === 0)
        n = n + elem
console.log("párosok összege: " + n)

console.log(q.filter(x => x % 2 === 0).reduce((s, x) => x + s, 0))


// 2. Feladat - csak páros számok
let m = [
    [1, 2, 3],
    [6, 8, 0], 
    [1, 1, 1],
    [2, 2, 2]
]

n = 0
let b = true
for (let elem of m) {
    b = true
    for (let elemofelem of elem) {
        if (elemofelem % 2 != 0)
            b = false
    }
    if (b) {n = n + 1}
}
console.log("párosok sorok száma: " + n)
console.log(m.filter(x => x.every(y => y % 2 === 0)).length)



// 3. Feladat - legnagyobb átlagú szemüveges neve
let students = [
    {name: "Gábor", grade: 5, glasses: false},
    {name: "Csilla", grade: 4, glasses: false},
    {name: "Urmomsahoe", grade: 2, glasses: true}
]

atlag = 0
nev = ""
for (let elem of students) {
    if (elem.glasses === true && elem.grade > atlag) {
        atlag = elem.grade
        nev = elem.name
    }
}

console.log("Legnagyobb átlagú szemüveges diák: " + nev)



// 4. Feladat - q tömbben melyik a legnagyobb szám (reduce, Math fv)

n = 0
n = Math.max(q)
       
console.log("Legnagyobb szám a q tömbben: " + n)