module Opc03 where

import Data.List

type Room = String
type Air = Int
type Fire = Integer
type MagicLevel = Int

find :: [Room] -> [Room]
find ["2.620"] =  ["2.620"]
find _ = []

add :: (Integral magic1, Integral magic2, Num magic3) => magic1 -> magic2 -> magic3
add x y = fromIntegral x + fromIntegral y


prime_magic :: MagicLevel -> MagicLevel -> [MagicLevel]
prime_magic n m
   | (isPrime n) && n <= m = n : (prime_magic (n+1) m) 
   | n > m = []
   | otherwise = prime_magic (n+1) m


isPrime :: Int -> Bool
isPrime x = [y | y <- [2..x], x `mod` y == 0, x > y] == [] && x >= 2


compress :: Eq magic => [magic] -> [(magic, MagicLevel)]
compress [] = []
compress (x:xs) = (head a, length a):(compress (concat asd)) where 
     (a:asd) = group (x:xs)

decompress :: Eq magic => [(magic, MagicLevel)] -> [magic]
decompress [] = []
decompress ((x1,x2):xs) = (replicate x2 x1)++(decompress xs)
