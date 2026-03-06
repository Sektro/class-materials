module Lesson06 where

sum :: Num a => [a] -> a
sum [] = 0
sum (x:xs) = x + sum xs

last :: [a]{-nemüres-} -> a
last [x] = x
last (x:xs) = last xs

init :: [a]{-nemüres-} -> [a]
init [x] = []
init (xs:x) = [xs] ++ (init x)

minimum :: Ord a => [a]{-véges, nemüres-} -> a
minimum [a] = a
minimum (x:xs)= min x (minimum xs)

concat :: [[a]] -> [a]
concat [] = []
concat (xs:x) = xs ++ (concat x)

(++) :: [a] -> [a] -> [a]
(++) [] x = x
(++) (x:xs) y = x : ((++) xs y)

merge :: [a] -> [a] -> [a]
merge [] x = x
merge x [] = x
merge (x:xs) (y:ys) = x:y:(merge xs ys)

zip :: [a] -> [b] -> [(a,b)]
zip [] _ = []
zip _ [] = []
zip (x:xs) (y:ys) = (x,y):(zip xs ys)

isPrefixOf :: Eq a => [a] -> [a] -> Bool
isPrefixOf [] _ = True
isPrefixOf _ [] = False
isPrefixOf (x:ys) (y:xs) = x==y && isPrefixOf ys xs

elem :: Eq a => a -> [a]{-véges-} -> Bool
elem _ [] = False
elem x (ys:y) = x==ys || elem x y

--nub :: Eq a => [a] -> [a]