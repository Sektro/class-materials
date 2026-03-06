module Homework5 where
import Data.List
import Data.Char

add2 :: Int -> Int -> (Int,Int)
add2 0 0 = (0,0)
add2 1 1 = (0,1)
add2 1 0 = (1,0)
add2 0 1 = (1,0)

paren :: Char -> Char -> Bool
paren '(' ')' = True
paren '[' ']' = True
paren '{' '}' = True
--ez a rész nem tudom kell-e
paren ')' '(' = True
paren ']' '[' = True
paren '}' '{' = True
--de azért ideírtam 
paren _ _ = False

calc :: (Int, Char ,Int) -> Int
calc (x, '+', y) = x + y
calc (x, '-', y) = x - y
calc (x, '*', y) = x * y
calc (x, '/', y) = x `div` y

changeToUpper :: String -> String
changeToUpper [x] = [toUpper(x)]
changeToUpper [x,y,z] = [x, toUpper (y), z]
changeToUpper x = x

countAttackingQueens :: Integral a => (a,a) -> [(a,a)] -> a
countAttackingQueens (x,y) l = sum [1 | (a,b)<-l , x == a || y == b || abs(x-a) == abs (y-b)]

compress :: String -> [(Char, Int)]
compress l = zip [y | x<-[1..length (group l)], y<- [head(last(take (x) (group l)))]] [x | y<-[1..length (group l)], x<-[length(last(take (y) (group l)))]]

decompress :: [(Char, Int)] -> String
decompress l = [ z | (a,b)<-l, z<-[a], i<-[1..b]] 
