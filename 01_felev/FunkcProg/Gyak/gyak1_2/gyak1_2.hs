module Lesson01 where

-- komment
{- több
soros
komment -}

{-
logikai: Bool
egész számok: Int, Integer
lebegő pontos számok: Double, Float
karakterek: Char
-}

one :: Int
one = 1

two :: Int
two = 1 + one

f :: Integer
f = 3

inc :: Int -> Int
inc x = 1 + x

inc' :: Int -> Int
inc' x = inc (inc x)

letterE :: Char
letterE = 'E'

okay :: Bool
okay = True

isEven :: Int -> Bool
isEven a = a `mod` 2 == 0

isOdd :: Int -> Bool
isOdd x = x `mod` 2 /= 0

isOdd' :: Int -> Bool
isOdd' x = not(isEven x )

returnFirst :: Char -> Bool -> Char
returnFirst a b = a

add :: Int -> Int -> Int
add x y = x + y                    -- add a a = a + a --> ez nem jó, eltérő módon kell elnevezni a paramétereket 

greater :: Int -> Int -> Bool
greater szam1 szam2 = szam1 > szam2

greater' :: Int -> Int -> Bool
greater' szam1 szam2 = (>) szam1 szam2

-- Speciális karakterek : $ + - # ! > < 

{-

 - függvényhívás, operátorok prefix módon írva
 - ^, ^^, **
 - *, /, infix függvények
 - +, -
-}



sndlsBigger :: Int -> Int -> Bool
sndlsBigger a b = a < b

