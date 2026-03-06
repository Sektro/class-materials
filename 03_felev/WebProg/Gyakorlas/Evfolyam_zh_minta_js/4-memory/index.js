const inputCircleNumber = document.querySelector("#circle-number");
const buttonStart = document.querySelector("#start");
const divContainer = document.querySelector("#container");
const divOutput = document.querySelector("#output");

// Application state
let canGuess = false;
let solution = [];
let series = [];

// ========= Utility functions =========

function random(a, b) {
    return Math.floor(Math.random() * (b - a + 1)) + a;
}

function toggleHighlight(node) {
    node.classList.toggle("highlight");
    node.addEventListener("animationend", function () {
        node.classList.remove("highlight");
    }, { once: true });
}

// ========= Event Listeners =========

// 1. Handle range input change (update number of circles)
inputCircleNumber.addEventListener("input", function () {
    const numCircles = parseInt(inputCircleNumber.value);

    // Clear the container before adding new circles
    divContainer.innerHTML = '';

    // Create the circles based on the slider value
    for (let i = 0; i < numCircles; i++) {
        const circle = document.createElement("a");
        circle.classList.add("circle");
        divContainer.appendChild(circle);
    }
});

// 2. Start button click (generate series and start the game)
buttonStart.addEventListener("click", function () {
    const numCircles = parseInt(inputCircleNumber.value);

    if (numCircles < 3 || numCircles > 10) {
        alert("Please select a number between 3 and 10.");
        return;
    }

    // Generate the series with random numbers between 1 and numCircles
    series = [];
    for (let i = 0; i < 7; i++) {
        series.push(random(1, numCircles)); // Random number between 1 and numCircles
    }

    console.log(series);

    // Prevent guessing until the circles have flashed
    canGuess = false;

    // Flash the circles
    divOutput.innerHTML = "Flashing circles...";
    flashCircles(series);
});

// 3. Flash the circles in the series with a delay
function flashCircles(series, index = 0) {
    if (index < series.length) {
        const circle = divContainer.children[series[index] - 1]; // Get circle by index
        toggleHighlight(circle);

        // Wait for 1 second before flashing the next circle
        setTimeout(() => flashCircles(series, index + 1), 1000);
    } else {
        divOutput.innerHTML = "Now, your turn...";
        // Allow user to guess after all circles are flashed
        setTimeout(() => {
            canGuess = true;
        }, 1000); // Allow guessing after 1 second delay
    }
}

// 4. Handle circle click (user's guess)
divContainer.addEventListener("click", function (e) {
    if (!canGuess) return; // Prevent clicks before it's the user's turn
    
    const clickedCircle = e.target;

    if (!clickedCircle.classList.contains("circle")) return; // Ignore clicks outside circles
    
    // Get the index of the clicked circle
    const clickedIndex = Array.from(divContainer.children).indexOf(clickedCircle);
    
    // Add the clicked circle's number to the solution array
    solution.push(clickedIndex + 1); // We add 1 because series is 1-based

    console.log(solution);

    // Check if the solution array matches the series
    if (solution.length === 7) {
        if (JSON.stringify(solution) === JSON.stringify(series)) {
            divOutput.innerHTML = "Nice job!";
        } else {
            divOutput.innerHTML = "Failed!";
        }
    }
});
