module Lesson11 where

import Data.List
--import Prelude hiding (Maybe, (..))

--(v ez egy kulcsszó)                                                           (v ez kell a ghci-nek)
data Day = Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday deriving (Eq, Show, Ord) 

isFirstDayOfWeek :: Day -> Bool
isFirstDayOfWeek Monday = True
isFirstDayOfWeek _ = False

type Year = Int
type Square = (Char,Int)
type Two a = (a,a) -- (1,2) :: Two Int
type ListList a = [[a]]
type P2 a = (a,a)
type PredicteOn a = a -> Bool

--lambda newtype-tól kezdve "nem érzi a chi-t" 

type Hour = Int
type Minute = Int
data Time = T Hour Minute deriving (Show, Ord, Eq) --sorrend mindegy, rá értelmezhető műveleket adja meg
--kontruktor: T, ez egy fgv
{-
>:t T
>T :: Hour -> Minute -> Time
-}

showTime :: Time -> String
showTime (T h m) = (show h) ++ ":" ++ (show m) --show fgv listákat ad meg, így ++-t használunk

eqTime :: Time -> Time -> Bool
eqTime (T h1 m1) (T h2 m2)
    | h1 == h2 && m1 == m2 = True
    | otherwise = False

isEarlier :: Time -> Time -> Bool
isEarlier (T h1 m1) (T h2 m2)
    | h1 < h2 = True
    | h1 == h2 && m1 < m2 = True
    | otherwise = False

isBetween :: Time -> Time -> Time -> Bool
isBetween t1 t2 t3 = (isEarlier t1 t2 && isEarlier t2 t3) || (isEarlier t3 t2 && isEarlier t2 t1)

data USTime = AM Hour Minute | PM Hour Minute deriving (Eq, Show)

showUSTime :: USTime -> String
showUSTime (AM h m) = showTime(T h m) ++ " am"
showUSTime (PM h m) = showTime(T h m) ++ " pm"

usTimeToTime :: USTime -> Time
usTimeToTime (AM 12 m) = time 0 m
usTimeToTime (AM h m) = time h m1
usTimeToTime (PM 12 m) = time 0 m
usTimeToTime (PM h m) = time (12 + h) m

--maybe újradefiniálása     (v ez is egy konstruktor (Justt))
data Maybee a = Nothingg | Justt a --deriving Show   <-- ezt egy későbbi instance-al helyettesítjük

head' :: [a] -> Maybee a
head' [] = Nothingg
head' (x:xs) = Justt x

out :: Maybee a -> a
out (Justt a) = a
out Nothingg = error "hiba"

isJust :: Maybee a -> Bool
isJust (Justt _) = True
isJust _ = False

instance Show a => (Show (Maybee a)) where
    show (Justt  a) = show a
    show Nothingg = "Nothing"

--alábbiakhoz: import Data.Maybe
--fromMaybe (pl.: 10 (Just 20) vagy 10 (Nothing))
{- :i Either -}