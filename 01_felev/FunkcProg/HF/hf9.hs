module Homework09 where

import Data.List

type Snake = [String]

mayStartWith :: [String] -> String
startsWith :: Char -> [String] -> [String]
endsWith :: Char -> [String] -> [String]
snakeEndsWith :: Snake -> Char
step :: Snake -> [String] -> [Snake]

{-
mayStartWith :: [String] -> String
startsWith :: Char -> [String] -> [String]
endsWith :: Char -> [String] -> [String]
snakeEndsWith :: Snake -> Char
step :: Snake -> [String] -> [Snake]
-}

mayStartWith x =  nub (map head x)

startsWith x [] = []
startsWith x (y:ys) 
    | head (y)==x = y:(startsWith x ys)
    | otherwise = startsWith x ys

endsWith x [] = []
endsWith x (y:ys) 
    | last (y)==x = y:(endsWith x ys)
    | otherwise = endsWith x ys

snakeEndsWith x = last(last(x))

step _ [] = []
step [x] (y:ys)
     | (last(x)) == (head(y)) = [[x]++[y]]++(step [x] ys)
     | otherwise = step [x] ys