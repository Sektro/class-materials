const span = document.querySelectorAll("span")[0]
const span2 = document.querySelectorAll("span")[1]

const numbers = [5, 2, 15, -3, 6, -8, -2];

const matrix = [
  [1, 0, 3],
  [0, 2, 0],
  [4, 5, 6],
  [0, 0, 0],
]

const searchResults = {
  Search: [
    {
      Title: "The Hobbit: An Unexpected Journey",
      Year: 2012,
      imdbID: "tt0903624",
      Type: "movie"
    },
    {
      Title: "The Hobbit: The Desolation of Smaug",
      Year: 2013,
      imdbID: "tt1170358",
      Type: "movie"
    },
    {
      Title: "The Hobbit: The Battle of the Five Armies",
      Year: 2014,
      imdbID: "tt2310332",
      Type: "movie"
    },
    {
      Title: "The Hobbit",
      Year: 1977,
      imdbID: "tt0077687",
      Type: "movie"
    },
    {
      Title: "Lego the Hobbit: The Video Game",
      Year: 2014,
      imdbID: "tt3584562",
      Type: "game"
    },
    {
      Title: "The Hobbit",
      Year: 1966,
      imdbID: "tt1686804",
      Type: "movie"
    },
    {
      Title: "The Hobbit",
      Year: 2003,
      imdbID: "tt0395578",
      Type: "game"
    },
    {
      Title: "A Day in the Life of a Hobbit",
      Year: 2002,
      imdbID: "tt0473467",
      Type: "movie"
    },
    {
      Title: "The Hobbit: An Unexpected Journey - The Company of Thorin",
      Year: 2013,
      imdbID: "tt3345514",
      Type: "movie"
    },
    {
      Title: "The Hobbit: The Swedolation of Smaug",
      Year: 2014,
      imdbID: "tt4171362",
      Type: "movie"
    }
  ],
  totalResults: 51,
  Response: true
}

let matrix2 = []
for (let n of matrix) {
  matrix2[matrix2.length] = []
  for (let i of n) {
    matrix2[matrix2.length-1][(matrix2[matrix2.length-1]).length] = i**2
  }
}
span.innerHTML = matrix2.join(", ")
console.log(matrix2)

let array = []
for (let n of matrix) {
  for (let i of n) {
    array[array.length] = i 
  }
}
span2.innerText = Math.min(...array)
console.log(Math.min(...array))