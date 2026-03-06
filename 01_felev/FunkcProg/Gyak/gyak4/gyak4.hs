module Lesson4 where
{-
1 - 2^10:

[2^n | n <- [0..10]]

False, Bool 10-szer felváltva:
[even n | n <- [0..9]]

Legkisebb 2 hatvány > 10^20:
head [n | n <- [0..], 2^n > 10^20]

Soroljuk fel a 60 osztóit!
[n | n <- [1..60], 60 `mod` n == 0]

Hány osztója van a 60-nak?
length [n | n <- [1..60], 60 `mod` n == 0]

Prímszám-e az 123457?
null [n | n <- [2..(div 123457 2)], 123457 `mod` n == 0]

Állítsuk elő azt a listát, amely sorrendben tartalmazza az összes (óra, perc) párt!
[ (n,m) | n <- [0..23] , m <- [0..59]]

Állítsuk elő azt a listát, amely párként tartalmazza az összes dominót: [(0,0),(0,1),...,(0,9),(1,1),...,(9,9)]!
[ (n,m) | n <- [0..9] , m <- [0..9], not(m < n)]
[ (n,m) | n <- [0..9] , m <- [n..9]]

-}

--Mintaillesztés

(.&&.) :: Bool -> Bool -> Bool
(.&&.) True True = True
(.&&.) True False = False
(.&&.) True False = False
(.&&.) False False = False

--változók: x, y, a, xs
--joker: _
--típus specifikus minta: True, 0, (a,b)
	-- Bool: True, False
	-- Char: 'a', '\n', 'A'
	-- Int: 0,4,7,2345
	-- Double: 3.14, 6.87654
	-- String: "", "alma", "kiscica"
	-- Tuple: (a,b), (x,xs,z,d)
	-- lista:
		-- üres lista: []
		-- pontosan 1 elemű: [a]
		-- pontosan 3 elemű: [x,y,z]
		-- legalább 1 elemű: (x:xs)
		-- legalább 3 elemű: (x:y:z:xs)  <- nem kell xs-nek lennie, ez csak egy szokás
		
(.&&.)' :: Bool -> Bool -> Bool
(.&&.)' True True = True
(.&&.)' _ _ = False
