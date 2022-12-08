insert into dagen(datum)
values ('1000-01-01'), ('2999-12-31');

insert into sprekers(voornaam, familienaam, titel, firma)
values ('testVoornaam', 'testFamilienaam', 'superProgrammeur', 'TheSkyIsTheLimit');
insert into sessies(naam, dagId, uur, sprekerid, interesses)
values ('testSessie',
        (select id from dagen where datum = '2999-12-31'),
        '23:59:59',
        (select id from sprekers where voornaam = 'testVoornaam'),
        1);

insert into sprekers(voornaam, familienaam, titel, firma)
values ('testVoornaam2', 'testFamilienaam2', 'superProgrammeur2', 'TheSkyIsTheLimit2');
insert into sessies(naam, dagId, uur, sprekerid, interesses)
values ('testSessie2',
        (select id from dagen where datum = '2999-12-31'),
        '00:00:01',
        (select id from sprekers where voornaam = 'testVoornaam2'),
        1);
