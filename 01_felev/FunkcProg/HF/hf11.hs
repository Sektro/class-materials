module Homework11 where


data Either' a b = L' a | R' b deriving (Show, Ord, Eq)

collectL :: [Either' a b] -> [Either' a b]
collectL [] = []
collectL ((L' x):xs) = (L' x):(collectL xs)
collectL ((R' x):xs) = collectL xs