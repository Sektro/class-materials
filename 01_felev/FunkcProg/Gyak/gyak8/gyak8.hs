module Lesson08 where 
--import Prelude hiding (unzip, splitAt,...)
--Ezt akkor, ha nem akarsz mindegyikhez egy vesszőt rakni, ami már eleve definiálva van Preludeban



unzip' :: [(a, b)] -> ([a], [b])
unzip' [] = ([],[])
unzip' ((a,b):xs) = (a:as,b:bs) where
    (as,bs) = unzip' xs

splitAt' :: Int -> [a] -> ([a], [a])
splitAt' n l | n <= 0 = ([],l)
splitAt' n [] = ([],[])
splitAt' n (x:xs) = (x: l1, l2) where
    (l1,l2) = splitAt' (n-1) xs

split :: [a] -> ([a], [a])
split [] = ([],[])
split (x:xs) = ( x : l2 , l1 ) where
    (l1,l2) = split xs

msort :: Ord a => [a] -> [a]
msort [] = []
msort [x] = [x]
msort l = msort as `sortMerge` msort bs where
    (as,bs) = split l
--msort nem működik sortMerge definiálása nélkül (lambdán esetszétválasztás)

map :: (a -> b) -> [a] -> [b]
map f l = [ f x | x<-l ]

{-
map f [] = []
map f (x:xs) = f x : map f xs
-}

filter' :: (a -> Bool) -> [a] -> [a]
filter' f [] = []
filter' f (x:xs) 
    | f x = x : filter' f xs
    | otherwise = filter' f xs

takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' f [] = []
takeWhile' f (x:xs)
    | f x = x : takeWhile' f xs
    | otherwise = takeWhile' f []

dropWhile' :: (a -> Bool) -> [a] -> [a]
dropWhile' f [] = []
dropWhile' f l@(x:xs)
   | f x = dropWhile' f xs
   | otherwise = l

span' :: (a -> Bool) -> [a]{-véges-} -> ([a],[a])
span' f [] = ([],[])
span' f (x:xs) 
    | f x = (x:as,bs)
    | otherwise = ([], x:xs)
    where (as,bs) = span' f xs

iterate' :: (a -> a) -> a -> [a]  
iterate' f x = x : iterate' f (f x)

($) :: (a -> b) -> a -> b
($) f a = f a
--használat: zárójelek eltüntetése
--pl.: sin $ cos $ 4 + log 1    ==    sin ( cos ( 4 + log 1))