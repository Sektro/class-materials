module Homework6 where

pairWiseAdd :: [Int] -> [Int] -> [Int]
pairWiseAdd [] x = []
pairWiseAdd x [] = []
pairWiseAdd (i:l) (y:yx) = (i+y):(pairWiseAdd l yx) 

lucas :: (Eq a, Num a)=> a -> a
lucas 0 = 2
lucas 1 = 1
lucas n = lucas(n-1) + lucas (n-2)

longerThan :: Integral i => [a] -> i -> Bool
longerThan [] 0 = False
longerThan [] x = not (x>0)
longerThan _ 0 = True
longerThan (zigg:zagg) zugg = (zugg<0)||(longerThan zagg (zugg-1))

format :: Integral a => a -> [Char] -> [Char]
format 0 zs = zs
format u [] = [' '|x<-[1..u]]
format g (h:i) = h:(format (g-1) (i))

reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = (reverse' xs) ++ [x]
