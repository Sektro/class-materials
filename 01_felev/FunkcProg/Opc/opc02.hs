module Opc02 where

type CurrentDistrict = Int
type NextDistrict = Int
type HealthDamage = Int
type ArmorDamage = Int
type Health = Int
type Armor = Int
type Enhance = Int

move:: (CurrentDistrict , NextDistrict) -> NextDistrict
move (x,y) = y

arcane_missiles :: (HealthDamage , ArmorDamage) -> (Health , Armor) -> (Health , Armor)
arcane_missiles (hd,ad) (h,a) = (h-hd,a-ad)

arcane_missiles_mark_1 :: Enhance -> (HealthDamage , ArmorDamage) -> (Health , Armor) -> (Health , Armor)
arcane_missiles_mark_1 e (hd,ad) (h,a) = (h-hd*e,a-ad*e)

arcane_blast :: (HealthDamage , ArmorDamage) -> (HealthDamage , ArmorDamage) -> (Health , Armor) -> (Health , Armor)
arcane_blast (hd1,ad1) (hd2,ad2) (h,a) = (h-(hd1*hd2+ad1*ad2),a-(hd1*hd2+ad1*ad2))