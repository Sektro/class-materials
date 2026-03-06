const button5 = document.querySelector("#button5")
const button7 = document.querySelector("#button7")
let difficulty = 5
const table = document.querySelector("table")
const startGame = document.querySelector("#start-game")
const menu = document.querySelector("#menu")
const actualGame = document.querySelector("#actual-game")
actualGame.style.display = "none"
const playerNameInput = document.querySelector("#name-input")
let playerName
const playerNameOutput = document.querySelector("#player")
const description = document.querySelector("#description")
description.style.display = "none"
const descriptionButton = document.querySelector("#description-button")
const descriptionCloser = document.querySelector("#close-description")


//board
let board = [
    [],
    [],
    [],
    [],
    []
]


/*

----HELP----
lands:
    plain: 0
    lake: -1
    bridge: 1,2
    mountain: 3,4,5,6

rail:
    none: 0
    straight: 1 (vertical), 2 (horizontal)
    curved: 3 (right-bottom), 4 (bottom-left), 5 (left-up), 6 (up-right)

------------

*/
paintBoard()

function paintBoard() {
    if (difficulty === 5) {
        table.innerHTML = 
        [...Array(5).fill(0).keys()].map(row => `<tr>${
            [...Array(5).fill(0).keys()].map(cell => `<td></td>`).join("")
        }</tr>`).join("")
        switch (Math.floor(Math.random() * 5)) {
            case 0 :
                table.style.backgroundImage = "url('pics/levels/easy/level_e1.png')"
                board = [
                    [{land:0,rail:0},{land:4,rail:0},{land:0,rail:0},{land:0,rail:0},{land:-1,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:1,rail:0},{land:-1,rail:0}],
                    [{land:1,rail:0},{land:0,rail:0},{land:5,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:-1,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:6,rail:0},{land:0,rail:0},{land:0,rail:0}]
                ]
                break
            case 1 :
                table.style.backgroundImage = "url('pics/levels/easy/level_e2.png')"
                board = [
                    [{land:-1,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:5,rail:0},{land:0,rail:0},{land:0,rail:0},{land:5,rail:0}],
                    [{land:1,rail:0},{land:-1,rail:0},{land:6,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:-1,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}]
                ]
                break
            case 2 :
                table.style.backgroundImage = "url('pics/levels/easy/level_e3.png')"
                board = [
                    [{land:0,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:1,rail:0}],
                    [{land:0,rail:0},{land:5,rail:0},{land:1,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:-1,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:0,rail:0},{land:5,rail:0}]
                ]
                break
            case 3 :
                table.style.backgroundImage = "url('pics/levels/easy/level_e4.png')"
                board = [
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:1,rail:0},{land:0,rail:0},{land:4,rail:0},{land:0,rail:0},{land:4,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:-1,rail:0},{land:6,rail:0},{land:0,rail:0}]
                ]
                break
            case 4 :
                table.style.backgroundImage = "url('pics/levels/easy/level_e5.png')"
                board = [
                    [{land:0,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:3,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:1,rail:0},{land:0,rail:0},{land:0,rail:0},{land:6,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:1,rail:0},{land:-1,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:5,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}]
                ]
                break
            default :
                console.log("Something went wrong!")
        }
    }
    else if (difficulty === 7) {
        table.innerHTML = 
        [...Array(7).fill(0).keys()].map(row => `<tr>${
            [...Array(7).fill(0).keys()].map(cell => `<td></td>`).join("")
        }</tr>`).join("")
        switch (Math.floor(Math.random() * 5)) {
            case 0 :
                table.style.backgroundImage = "url('pics/levels/hard/level_d1.png')"
                board = [
                    [{land:0,rail:0},{land:4,rail:0},{land:-1,rail:0},{land:-1,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0}],
                    [{land:1,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:1,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:6,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:6,rail:0},{land:0,rail:0},{land:4,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:-1,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}]
                ]
                break
            case 1 :
                table.style.backgroundImage = "url('pics/levels/hard/level_d2.png')"
                board = [
                    [{land:0,rail:0},{land:0,rail:0},{land:-1,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:1,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:0,rail:0},{land:5,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:1,rail:0}],
                    [{land:3,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:-1,rail:0},{land:0,rail:0},{land:4,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:3,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:-1,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}]
                ]
                break
            case 2 :
                table.style.backgroundImage = "url('pics/levels/hard/level_d3.png')"
                board = [
                    [{land:0,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:1,rail:0}],
                    [{land:-1,rail:0},{land:0,rail:0},{land:6,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:-1,rail:0},{land:6,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:1,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:4,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:-1,rail:0},{land:6,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}]
                ]
                break
            case 3 :
                table.style.backgroundImage = "url('pics/levels/hard/level_d4.png')"
                board = [
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:1,rail:0},{land:0,rail:0},{land:5,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:6,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:2,rail:0},{land:0,rail:0},{land:-1,rail:0},{land:0,rail:0},{land:2,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:5,rail:0},{land:0,rail:0},{land:4,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:1,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:6,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}]
                ]
                break
            case 4 :
                table.style.backgroundImage = "url('pics/levels/hard/level_d5.png')"
                board = [
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:3,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:2,rail:0},{land:2,rail:0},{land:0,rail:0},{land:4,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:3,rail:0},{land:0,rail:0},{land:-1,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:5,rail:0},{land:0,rail:0},{land:1,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}],
                    [{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0},{land:0,rail:0}]
                ]
                break
            default :
                console.log("Something went wrong!")
        }
    }
}

function xyCoord(td) {
    return {
    x: td.cellIndex,
    y: td.parentNode.rowIndex
    };
}

function checkForGameEnd() {
    let i = 0
    let j = 0
    let ended = true
    while (ended && i < difficulty) {
        j=0
        while (ended && j < difficulty) {
            if (board[i][j].rail === 0 && board[i][j].land !== -1) {ended = false}
            /* no time to finish
            if (i === 0)  {
                if (j === 0) {
                    switch (board[y][x].rail) {
                        case 0 :
                            break
                        case 1:
                            ended = false
                            break
                        case 2:
                            ended = false
                            break
                        case 3:
                            if (!(board[i][j+1].rail === 2 || board[i][j+1].rail === 4)) {ended = false}
                            if (!(board[i][j].rail === 2 || board[i][j+1].rail === 3)) {ended = false}
                            break
                        case 4:
                            ended = false
                            break
                        case 5:
                            ended = false
                            break
                        case 6:
                            ended = false
                            break
                        default :
                            console.log("Something went wrong!")
                    }
                }
                else if (j === difficulty-1) {
                    switch (board[y][x].rail) {
                        case 0 :
                            break
                        case 1:
                            ended = false
                            break
                        case 2:
                            ended = false
                            break
                        case 3:
                            ended = false
                            break
                        case 4:
                            if (!(board[i][j+1].rail === 2 || board[i][j+1].rail === 3)) {ended = false}
                            break
                        case 5:
                            ended = false
                            break
                        case 6:
                            ended = false
                            break
                        default :
                            console.log("Something went wrong!")
                    }
                }
                else {
                    switch (board[y][x].rail) {
                        case 0 :
                            break
                        case 1:
                            ended = false
                            break
                        case 2:
                            if (!(board[i][j+1].rail === 2 || board[i][j+1].rail === 3)) {ended = false}
                            break
                        case 3:
                            break
                        case 4:
                            break
                        case 5:
                            ended = false
                            break
                        case 6:
                            ended = false
                            break
                        default :
                            console.log("Something went wrong!")
                    }
                }
            }
            else if (i === difficulty-1) {
                if (j === 0) {
                    switch (board[y][x].rail) {
                        case 0 :
                            break
                        case 1:
                            ended = false
                            break
                        case 2:
                            ended = false
                            break
                        case 3:
                            ended = false
                            break
                        case 4:
                            ended = false
                            break
                        case 5:
                            ended = false
                            break
                        case 6:
                            break
                        default :
                            console.log("Something went wrong!")
                    }
                }
                else if (j === difficulty-1) {
                    switch (board[y][x].rail) {
                        case 0 :
                            break
                        case 1:
                            ended = false
                            break
                        case 2:
                            ended = false
                            break
                        case 3:
                            ended = false
                            break
                        case 4:
                            ended = false
                            break
                        case 5:
                            break
                        case 6:
                            ended = false
                            break
                        default :
                            console.log("Something went wrong!")
                    }
                }
                else {
                    switch (board[y][x].rail) {
                        case 0 :
                            break
                        case 1:
                            
                            break
                        case 2:
                            break
                        case 3:
                            break
                        case 4:
                            break
                        case 5:
                            break
                        case 6:
                            break
                        default :
                            console.log("Something went wrong!")
                    }
                }
            }
            else {
                if (j === 0) {
                    
                }
                else if (j === difficulty-1) {

                }
                else {
                    switch (board[y][x].rail) {
                        case 0 :
                            break
                        case 1:
                            
                            break
                        case 2:
                            break
                        case 3:
                            break
                        case 4:
                            break
                        case 5:
                            break
                        case 6:
                            break
                        default :
                            console.log("Something went wrong!")
                    }
                }
            }
            */
            ++j
        }
        ++i
    }
    if (ended) {
        backToMainMenu()
    }
}

table.addEventListener("click", function(e) {
    if (!e.target.matches("td")) return;
    const td = e.target
    const { x , y } = xyCoord(td)
    if (board[y][x].land === -1) {return}
    if (board[y][x].rail === 0) {
        switch (board[y][x].land) {
            case 0 :
                board[y][x].rail = 1
                td.style.backgroundImage = "url('pics/tiles/straight_rail.png')"
                td.style.transform = 'rotate(0deg)'
                break
            case 1:
                board[y][x].rail = 1
                td.style.backgroundImage = "url('pics/tiles/bridge_rail.png')"
                td.style.transform = 'rotate(0deg)'
                break
            case 2:
                board[y][x].rail = 2
                td.style.backgroundImage = "url('pics/tiles/bridge_rail.png')"
                td.style.transform = 'rotate(90deg)'
                break
            case 3:
                board[y][x].rail = 3
                td.style.backgroundImage = "url('pics/tiles/mountain_rail.png')"
                td.style.transform = 'rotate(0deg)'
                break
            case 4:
                board[y][x].rail = 4
                td.style.backgroundImage = "url('pics/tiles/mountain_rail.png')"
                td.style.transform = 'rotate(90deg)'
                break
            case 5:
                board[y][x].rail = 5
                td.style.backgroundImage = "url('pics/tiles/mountain_rail.png')"
                td.style.transform = 'rotate(180deg)'
                break
            case 6:
                board[y][x].rail = 6
                td.style.backgroundImage = "url('pics/tiles/mountain_rail.png')"
                td.style.transform = 'rotate(270deg)'
                break
            default :
                console.log("Something went wrong!")
        }
    }
    else if (board[y][x].rail === 1 || board[y][x].rail === 2) {
        switch (board[y][x].land) {
            case 0 :
                board[y][x].rail = 3
                td.style.backgroundImage = "url('pics/tiles/curve_rail.png')"
                td.style.transform = 'rotate(0deg)'
                break
            case 1:
                board[y][x].rail = 0
                td.style.backgroundImage = "url('pics/tiles/bridge.png')"
                td.style.transform = 'rotate(0deg)'
                break
            case 2:
                board[y][x].rail = 0
                td.style.backgroundImage = "url('pics/tiles/bridge.png')"
                td.style.transform = 'rotate(90deg)'
                break
            default :
                console.log("Something went wrong!")
        }
    }
    else if (board[y][x].rail > 2 && board[y][x].rail < 7) {
        switch (board[y][x].land) {
            case 0 :
                board[y][x].rail = 0
                td.style.backgroundImage = "url('pics/tiles/empty.png')"
                td.style.transform = 'rotate(0deg)'
                break
            case 3:
                board[y][x].rail = 0
                td.style.backgroundImage = "url('pics/tiles/mountain.png')"
                td.style.transform = 'rotate(0deg)'
                break
            case 4:
                board[y][x].rail = 0
                td.style.backgroundImage = "url('pics/tiles/mountain.png')"
                td.style.transform = 'rotate(90deg)'
                break
            case 5:
                board[y][x].rail = 0
                td.style.backgroundImage = "url('pics/tiles/mountain.png')"
                td.style.transform = 'rotate(180deg)'
                break
            case 6:
                board[y][x].rail = 0
                td.style.backgroundImage = "url('pics/tiles/mountain.png')"
                td.style.transform = 'rotate(270deg)'
                break
            default :
                console.log("Something went wrong!")
        }
    }
    checkForGameEnd()
})
table.addEventListener('contextmenu', function(e) {
    e.preventDefault();
    if (!e.target.matches("td")) return;
    const td = e.target
    const { x , y } = xyCoord(td)
    if (board[y][x].land !== 0) {return}
    else {
        switch (board[y][x].rail) {
            case 0 :
                break
            case 1:
                board[y][x].rail = 2
                td.style.backgroundImage = "url('pics/tiles/straight_rail.png')"
                td.style.transform = 'rotate(90deg)'
                break
            case 2:
                board[y][x].rail = 1
                td.style.backgroundImage = "url('pics/tiles/straight_rail.png')"
                td.style.transform = 'rotate(0deg)'
                break
            case 3:
                board[y][x].rail = 4
                td.style.backgroundImage = "url('pics/tiles/curve_rail.png')"
                td.style.transform = 'rotate(90deg)'
                break
            case 4:
                board[y][x].rail = 5
                td.style.backgroundImage = "url('pics/tiles/curve_rail.png')"
                td.style.transform = 'rotate(180deg)'
                break
            case 5:
                board[y][x].rail = 6
                td.style.backgroundImage = "url('pics/tiles/curve_rail.png')"
                td.style.transform = 'rotate(270deg)'
                break
            case 6:
                board[y][x].rail = 3
                td.style.backgroundImage = "url('pics/tiles/curve_rail.png')"
                td.style.transform = 'rotate(0deg)'
                break
            default :
                console.log("Something went wrong!")
        }
    }
    checkForGameEnd()
    return false;
}, false);


//timer
function displaySeconds(n) {
    let displayedText = ""
    let minutes = Math.floor(n / 60)
    let seconds = n - minutes * 60
    if (seconds > 9) {
        displayedText = `${minutes}:${seconds}`
    }
    else {
        displayedText = `${minutes}:0${seconds}`
    }
    return displayedText
}
let timer
let seconds = 0
const timerDisplay = document.querySelector("#time-passed")
function startTimer() {
    if (!timer) {
        timer = setInterval(() => {
            seconds++
            timerDisplay.textContent = displaySeconds(seconds)
        }, 1000)
    }
}


//player name
function setPlayerName() {
    playerName = playerNameInput.value
    playerNameOutput.innerHTML = playerName
}




//menu buttons
button5.addEventListener("click",function() {
    difficulty = 5
    button5.style.color = "rgb(80,81,104)"
    button7.style.color = "rgb(180, 191, 163)"
    button5.style.backgroundColor = "rgb(234, 239, 211)"
    button7.style.backgroundColor = "white"
})
button7.addEventListener("click",function() {
    difficulty = 7
    button7.style.color = "rgb(80,81,104)"
    button5.style.color = "rgb(180, 191, 163)"
    button7.style.backgroundColor = "rgb(234, 239, 211)"
    button5.style.backgroundColor = "white"
})
startGame.addEventListener("click",function() {
    setPlayerName()
    paintBoard()
    seconds = 0
    timerDisplay.textContent = displaySeconds(seconds)
    timer = null
    startTimer()
    actualGame.style.display = "grid"
    menu.style.display = "none"
})
function backToMainMenu() {
    clearInterval(timer)
    actualGame.style.display = "none"
    menu.style.display = "grid"
}
descriptionButton.addEventListener("click",function() {
    description.style.display = "grid"
    menu.style.display = "none"
})
descriptionCloser.addEventListener("click",function() {
    description.style.display = "none"
    menu.style.display = "grid"
})