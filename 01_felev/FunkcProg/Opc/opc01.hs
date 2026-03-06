module Opc01 where

lesser_heal :: Int -> Int
lesser_heal x = x + 3

lookout :: Int -> Int -> Bool
lookout x y = x > y `div` 10

volume :: Int -> Int -> Int
volume x y = x + (x `mod` 12) * y 