module Homework10 where
import Data.List

type Practice = String
type Hours = Integer
type Note = (Practice, Hours)
type TrainingCalendar = [Note]

type CompactCalendar = [Note]

sumUp :: TrainingCalendar -> Hours --[(b,a)] -> [a]
equalsTo :: Note -> Note -> Bool
practices :: TrainingCalendar -> [Practice]
notesFor :: Practice -> TrainingCalendar -> [Note]
totals :: TrainingCalendar -> CompactCalendar
findKeyWithDefault :: (Practice -> Bool) -> Hours -> CompactCalendar -> Hours
hoursFor :: CompactCalendar -> Practice -> Hours
diffData :: CompactCalendar -> CompactCalendar -> CompactCalendar
planSucceeded :: TrainingCalendar -> CompactCalendar -> Bool

{-
sumUp :: TrainingCalendar -> Hours --[(String,Integer)] -> Integer
equalsTo :: Note -> Note -> Bool
practices :: TrainingCalendar -> [Practice]
notesFor :: Practice -> TrainingCalendar -> [Note]
totals :: TrainingCalendar -> CompactCalendar
findKeyWithDefault :: (Practice -> Bool) -> Hours -> CompactCalendar -> Hours
hoursFor :: CompactCalendar -> Practice -> Hours
diffData :: CompactCalendar -> CompactCalendar -> CompactCalendar
planSucceeded :: TrainingCalendar -> CompactCalendar -> Bool
-}

sumUp l = sum (map snd l)

equalsTo = (\x y -> fst(x) == fst(y))

practices y = [l|l<-u] where
    u = map head (group(sort([fst(k)|k<-y])))

--notesFor = (\x y -> map (fst(y)==x) y)
notesFor x y = [l|l<-y,fst(l)==x]

totals [] = []
totals (a:b:as)
    | fst(x)==fst(y) = totals((fst(x),snd(x)+snd(y)):xs)
    | otherwise = x:(totals (y:xs)) where
    (x:y:xs) = sort (a:b:as)
totals (l:ls) = l:(totals ls) 

findKeyWithDefault f n [] = n
findKeyWithDefault f n (x:xs)
    | f(fst(x)) = snd(x)
    | otherwise = findKeyWithDefault f n xs

hoursFor [] x = 0
hoursFor (y:ys) x
    | fst(y)==x = snd(y)
    | otherwise = 0 + hoursFor ys x

diffData _ [] = []
diffData x (y:ys) = (fst(y),snd(y)*(-1) + hoursFor x (fst(y))):(diffData x ys)

planSucceeded _ [] = True
planSucceeded x (y:ys)
    | (hoursFor x (fst(y))) < (snd(y)) = False
    | otherwise = planSucceeded x ys