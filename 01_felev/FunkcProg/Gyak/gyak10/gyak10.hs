module Lesson10 where

firstLetters :: String -> String
firstLetters = unwords . map (take 1) . words

monogram :: String -> String
monogram = unwords . map ((++ ".") . take 1) . words

uniq :: Ord a => [a]{-véges-} -> [a]
uniq = map head . group . sort

--repeated :: Ord a => [a]{-véges-} -> [a]
--repeated = map head . filter (\x -> (>1) . length) . group . sort 
--ez rossz

sublists :: [a] -> [[a]]
sublists = concatMap (init . tails) . tail . inits

until :: (a -> Bool) -> (a -> a) -> a -> a
until p f = head . filter p . iterate f

foldr :: (a -> b -> b) -> b -> [a] -> b
foldr f e [] = e
foldr f e l@(x:xs) = x `f` (foldr f e xs)

foldl :: (b -> a -> b) -> b -> [a] -> b
foldl _ e [] = e
foldl f e (x:xs) = foldl f (e `f` x) xs

--(1+(2+(3+(4+(5+0)))))
--(((((0+1)+2)+3)+4)+5)

sum' :: Num a => [a]{-véges-} -> a
sum' l = foldr (+) 0 l

product :: Num a => [a]{-véges-} -> a
product l = foldr (*) 1 l

and' :: [Bool]{-véges-} -> Bool
and' l = foldr (&&) True l

length :: [a]{-véges-} -> Int
length l = foldl (\x y -> x + 1) 0 l
--foldl nem mukszik vegtelen listara

minimum :: Ord a => [a]{-véges, nemüres-} -> a
minimum l = foldr1 (min) l

scanr :: (a -> b -> b) -> b -> [a] -> [b]
scanr f z [] = [z]
scanr f z (x:xs) = (x `f` y) : y :ys
    where (y:ys) = scanr f z xs
