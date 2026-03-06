/*
    let i = 0
    let j = 0
    let ended = true
    
    while (ended && i < difficulty) {
        j=0
        while (ended && j < difficulty) {
            if (board[i][j].rail === 0 && board[i][j].land !== -1) {ended = false}
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
            ++j
        }
        ++i
    }
        */