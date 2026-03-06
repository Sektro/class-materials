module Homework07 where

repeat' :: a -> [a]
repeat' x = x:(repeat' x)

combinations :: Int -> [a] -> [[a]]
--combinations _ [] = []
--combinations n (x:xs) | n>0 && n<=length(x:xs) = [x] ++ take (n-1) xs ++ combinations n xs 
--combinations n (x:xs) = [take (n-1) (x:xs) ++ [(x:xs)!!(y-1)] | y<-[n..length(x:xs)]]
combinations n _
    |n<0 = []
    |n==0 = [[]]
combinations n [] = []
combinations n (x:xs) = [ x:ys | ys<-combinations (n-1) xs ] ++ combinations n xs
--megbeszeles: combinations n l@(x:xs) = map (\ys -> x:ys) (combinations (n-1) xs) ++ combinations n xs
{-
combinations n (y:yas) | n>0 = [take (z-1) (y:yas) ++ [(reverse(take ((length(y:yas))-(z-1)) (reverse(y:yas))))!!(x-1)] | z<-[(n),(n-1)..1], x<-[1..((length(y:yas))-(z-1))]] 
                       | n==0 = [[]]
                       | otherwise = []
					   -}
--reverse(take (length(y:yas)-(n-1)) (reverse(y:yas)))
--combinations n (y:yas) | n>0 = [take (z) (y:yas) ++ [(y:yas)!!(x-1)] | z<-[(n-1),(n-2)..0], x<-[n..length(y:yas)]] ++ combinations n yas
--[last(take (x-1) (reverse(take ((length(y:yas))-z) (reverse(y:yas)))))] 
-- ++ combinations n yas
--combinations n (x:xs) = [take (z) (x:xs) ++ (combinations (n-z) (reverse(take (length(x:xs)-n) (reverse (x:xs)))))!!(y) | z<-[n,(n-1)..1], y<-[1..(length(x:xs)-n)]] ++ combinations n xs

splitAt' :: Int -> [a] -> ([a], [a])
--splitAt' n l = (take n l,reverse(take (length (l)-n) (reverse l)))
splitAt' = \n -> \y -> (take n y, drop n y)
--Itt sem tudtam hogy optimalizáljam a kódot magamtól

(!!!) :: [a] -> Int -> a
(!!!) l n 
          | n>0 && (n+1)>(sum [1|x<-(take (n+1) l)]) = error "Túl nagy index!"
          | n>0 = last(take (n+1) l)
          | n<0 && (n*(-1))<=length(l) = last(take ((-1)*n) (reverse l))
          | n<0 && (n*(-1))>length(l) = error "Túl kicsi index!"