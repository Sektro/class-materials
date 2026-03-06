const form = document.querySelector("form");
const divContainer = document.querySelector(".container");

//1
const inputAll = document.querySelectorAll("input");
let M = 0;
for (let i = 0; i < inputAll.length; ++i) {
    M +=  parseInt(inputAll[i].dataset.consumption);
}
console.log(M);

//2
let ci = [];
for (let i = 0; i < inputAll.length; ++i) {
    value = parseFloat(inputAll[i].value);
    max = parseFloat(inputAll[i].max);
    consumption = parseFloat(inputAll[i].dataset.consumption);
    ci.push((value/max)*consumption);
}
console.log(ci);


//3
//labelek sorrendje SZERENCSÉRE ugyanaz, mint az inputok sorrendje, ezért így fogjuk őket kezelni
const labelAll = document.querySelectorAll("label");
for (let i = 0; i < labelAll.length; ++i) {
    labelAll[i].style.width = (parseInt(ci[i]) / M * 100 ) + "%";
}

//4
for (let i = 0; i < inputAll.length; ++i) {
    inputAll[i].addEventListener("input", handleInput);
}

function handleInput(e) {
    ci = [];
    for (let i = 0; i < inputAll.length; ++i) {
        value = parseFloat(inputAll[i].value);
        max = parseFloat(inputAll[i].max);
        consumption = parseFloat(inputAll[i].dataset.consumption);
        ci.push((value/max)*consumption);
    }
    console.log(ci);
    for (let i = 0; i < labelAll.length; ++i) {
        labelAll[i].style.width = (parseInt(ci[i]) / M * 100 ) + "%";
    }
}