module Opc04 where

type Title = String
type Count = Integer

o_O_count :: Title -> Count
o_O_count [] = 0
o_O_count (x:xs)
   | x == 'o' || x == 'O' = 1 + (o_O_count xs)
   | otherwise = (o_O_count xs)

longerThan :: [item] -> Count -> Bool
longerThan [] _ = False
longerThan _ 0 = True
longerThan (x:xs) n = longerThan xs (n-1)

merge :: [magic] -> [magic] -> [magic]
merge [] x = x
merge x [] = x
merge (x:xs) (y:ys) = x:y:(merge xs ys)

starter :: Eq magic => [magic] -> [magic] -> Bool
starter [] _ = True
starter _ [] = False
starter (x:xs) (y:ys)
   | x == y = starter xs ys
   | otherwise = False

endings :: [magic] -> [[magic]]
endings [] = [[]]
endings (x:xs) = (x:xs):(endings xs)
