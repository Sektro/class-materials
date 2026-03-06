module Lesson07 where

nub :: Eq a => [a] -> [a]
nub [] = []
nub (x:xs) = x : nub [ e | e<-xs, e /=x]

slice :: [Int] -> [a] -> [[a]]
slice [] _ = []
slice (x:xs) l = (take x l) : (slice xs (drop x l))

--slice [] _ = []
--slice (x:xs) l = [(take x l)] ++ (slice xs (drop x l))

every :: Int -> [a] -> [a]
every n [] = []
every n l@(x:xs) = x : every n (drop n l)

tails :: [a] -> [[a]] 
tails [] = [[]]
tails l = l : tails (tail l)

inits :: [a] -> [[a]] 
inits [] = [[]]
inits l = inits (init l) ++ [l]

upperLower :: Char -> Char
upperLower a
    | isLower a = toUpper a
    | otherwise = toLower a

digitToInt :: Char -> Int
digitToInt c
    | isDigit c = ord c - ord '0'
    | c >= 'a' && c <= 'f' = ord c - ord 'a' + 10
    | c >= 'A' && c <= 'F' = ord c - ord 'A' + 10
    | otherwise = error "not a digit"

{-
(^) :: Num a => a -> Integer -> a
(^) x 0 = 1
(^) x n
    | even n = sqrt(x^(div n 2))
    | odd n = x * (x^(n-1))
-}

drop :: Int -> [a] -> [a]
drop n l | n <= 0 = l
drop n [] = []
drop n (x:xs) = drop (n-1) xs

insert :: Ord a => a -> [a] -> [a]
insert x [] = [x]
insert x (y:ys)
    | x < y = x:y:ys
    | otherwise = y : insert x ys