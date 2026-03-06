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
const winMessage = document.querySelector("#win-message")
winMessage.style.display = "none"
const winMessageText = document.querySelector("#win-message-text")
const descriptionButton = document.querySelector("#description-button")
const descriptionCloser = document.querySelector("#close-description")
const winMessageCloser = document.querySelector("#close-win-message")


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

function checkForGameEnd(x,y) {
    let turn = ""
    let ended = false
    let needToCheck = true
    let lakes = 0
    for (let i = 0; i < difficulty; ++i) {
        for (let j = 0; j < difficulty; ++j) {
            if (board[i][j].rail === 0 && board[i][j].land !== -1) {needToCheck = false}
            if (board[i][j].land === -1) {++lakes}
        }
    }
    let n = difficulty * difficulty - lakes
    if (needToCheck) {ended = checking(x,y,x,y,n,turn)}
    if (ended) {
        gameEndEvent()
    }
}
function checking(x,y,x0,y0,n,turn) {
    if (x >= difficulty || y >= difficulty || x < 0 || y < 0) {return false}
    if (board[y][x].rail === 0) {
        return false
    }
    else if (board[y][x].rail === 1) {
        switch (turn) {
            case "right","left" :
                return false
                break
            case "up":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x,y-1,x0,y0,n-1,"up")
                break
            case "bottom":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x,y+1,x0,y0,n-1,"bottom")
                break
            case "":
                return checking(x,y+1,x0,y0,n-1,"bottom")
                break
        }
    }
    else if (board[y][x].rail === 2) {
        switch (turn) {
            case "up","bottom" :
                return false
                break
            case "left":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x-1,y,x0,y0,n-1,"left")
                break
            case "right":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x+1,y,x0,y0,n-1,"right")
                break
            case "":
                return checking(x+1,y,x0,y0,n-1,"right")
                break
        }
    }
    else if (board[y][x].rail === 3) {
        switch (turn) {
            case "right","bottom" :
                return false
                break
            case "left":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x,y+1,x0,y0,n-1,"bottom")
                break
            case "up":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x+1,y,x0,y0,n-1,"right")
                break
            case "":
                return checking(x+1,y,x0,y0,n-1,"right")
                break
        }
    }
    else if (board[y][x].rail === 4) {
        switch (turn) {
            case "left","bottom" :
                return false
                break
            case "right":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x,y+1,x0,y0,n-1,"bottom")
                break
            case "up":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x-1,y,x0,y0,n-1,"left")
                break
            case "":
                return checking(x-1,y,x0,y0,n-1,"left")
                break
        }
    }
    else if (board[y][x].rail === 5) {
        switch (turn) {
            case "left","up" :
                return false
                break
            case "right":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x,y-1,x0,y0,n-1,"up")
                break
            case "bottom":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x-1,y,x0,y0,n-1,"left")
                break
            case "":
                return checking(x-1,y,x0,y0,n-1,"left")
                break
        }
    }
    else if (board[y][x].rail === 6) {
        switch (turn) {
            case "right","up" :
                return false
                break
            case "left":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x,y-1,x0,y0,n-1,"up")
                break
            case "bottom":
                if (x === x0 && y === y0 && n === 0) {
                    return true
                }
                return checking(x+1,y,x0,y0,n-1,"right")
                break
            case "":
                return checking(x+1,y,x0,y0,n-1,"right")
                break
        }
    }
    else {
        console.log("Something went wrong!")
    }
}
function gameEndEvent() {
    winMessage.style.display = "grid"
    winMessageText.innerHTML = "Gratulálunk! Megnyerted a játékot!\nA feladványt teljesítetted " + timerDisplay.textContent + " alatt!"
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
    checkForGameEnd(x,y)
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
    checkForGameEnd(x,y)
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
winMessageCloser.addEventListener("click",function() {
    winMessage.style.display = "none"
    backToMainMenu()
})