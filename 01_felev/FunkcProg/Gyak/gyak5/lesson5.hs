module Lesson05 where

mul3 :: Int -> Int -> Int
mul3 0 _ = 0
mul3 _ 0 = 0
mul3 1 x = x
mul3 x 1 = x
mul3 2 2 = 1

{-
replaceNewline :: Char -> Char
replaceNewline x | x == '\n' = ' '
                 | x /= '\n' = x 
-}

replaceNewline :: Char -> Char
replaceNewline '\n' = ' '
replaceNewline x = x

replaceNewlines :: String -> String
replaceNewlines x = [replaceNewline(i) | i<-x]
--i /= '\n' || (i == '\n') && (y == ' ')

swap_a_az :: String -> String
swap_a_az "a" = "az"
swap_a_az "az" = "a"
swap_a_az x = x

swapAll_a_az :: String -> String
swapAll_a_az s = unwords  [swap_a_az n | n<-(words s)]

isSingleton :: [a] -> Bool
isSingleton [x] = True
isSingleton _ = False

f :: Int -> Int -> [Int]
f x xs = [ x + y | x >= 3, y<-[2,5..xs]]