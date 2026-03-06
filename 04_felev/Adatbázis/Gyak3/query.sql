-- környezet halmazalapú: olyan, mintha a DISTINCT mindenhol ott lenne
-- select nev as death_of_an_optimist from szeret where gyumolcs = 'alma'
-- select distinct sz1.nev from szeret as sz1, szeret as sz2 where sz1.nev = sz2.nev and sz1.gyumolcs <> sz2.gyumolcs
select * from dolgozo