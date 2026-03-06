const task1 = document.querySelector("#task1");
const task2 = document.querySelector("#task2");
const task3 = document.querySelector("#task3");
const task4 = document.querySelector("#task4");
const task5 = document.querySelector("#task5");

// data
console.log(products);

//1
let fullPrice = 0;
for (let e of products) {
    fullPrice += e.price * e.stock;
}
task1.innerText = fullPrice.toLocaleString('hu-HU', { useGrouping: true }) + " Ft";

//2
let order = [];
for (let e of products) {
    if (e.stock === 0) {
        order.push(e.name);
    }
}
orderText = order.join(", ");
task2.innerHTML = orderText;

//3
let orderCount = 0;
let orderProfit = 0;
let orderProfitAverage = 0;
for (let p of products) {
    for (let s in p.sales) {
        orderCount += 1;
        orderProfit += p.price * p.sales[s];
    }
}
orderProfitAverage += orderProfit / orderCount;
task3.innerHTML = orderProfitAverage;   

//4
let maxName = "";
let maxBuys = 0;
let currentBuys = 0;
for (let p of products) {
    for (let i = 1; i < 31; ++i) {
        if (i < 10) {
            if (p.sales["2024-11-0" + i] !== undefined) {
                currentBuys += p.sales["2024-11-0" + i];
            }
        }
        else {
            if (p.sales["2024-11-" + i] !== undefined) {
                currentBuys += p.sales["2024-11-" + i];
            }
        }
    }
    if (currentBuys > maxBuys) {
        maxBuys = currentBuys;
        maxName = p.name;
    }
    currentBuys = 0;
}
task4.innerHTML = maxName + " (" + maxBuys + "db)";


//5 
//sorold fel az összes eléhető márkát illetve a hozzájuk tartozó elérhető raktárkészletet.

let alreadyIn = false;
let brands = [];
for (p of products) {
    for (b of brands) {
        if (p.brand === b.name) {
            alreadyIn = true;
            b.stock += p.stock;
        }
    }
    if (!alreadyIn) {
        brands.push({
            name: p.brand,
            stock: p.stock
        });
    }
    alreadyIn = false;
}
result = [];
for (let i = 0; i < brands.length; ++i) {
    result.push(brands[i].name + ": " + brands[i].stock);
}
task5.innerHTML = result.join(", ");