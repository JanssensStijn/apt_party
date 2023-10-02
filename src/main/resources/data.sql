--INSERT INTO "PUBLIC"."VENUE" (ID, CAPACITY, DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM,
--                              FOOD_PROVIDED, FREE_PARKING_AVAILABLE,INDOOR,OUTDOOR,
  --                            CITY, LINK_MORE_INFO,VENUE_NAME)VALUES
    --{(1, 20000, 1, TRUE, FALSE, TRUE, FALSE, 'Antwerpen', 'https://www.sportpaleis.be/', 'Sportpaleis'),
    --(2, 1000, 1, TRUE, TRUE, TRUE, TRUE, 'Rillaar', 'https://rietlaer.be/', 't Rietlaer'),
    --(3,15000, 2, FALSE, FALSE, TRUE, FALSE, 'Antwerpen', 'https://www.lotto-arena.be/nl', 'Lotto-Arena'),
    --(4,17500, 3, FALSE, FALSE, TRUE, FALSE, 'Hasselt', 'https://www.trixxo-arena.be/', 'Trixxo-Arena'),
    --(4,17500, 3, FALSE, FALSE, TRUE, FALSE, 'Hasselt', 'https://www.trixxo-arena.be/', 'Trixxo-Arena'),
                                                                 -- };
insert into VENUE (
    ID, VENUE_NAME, LINK_MORE_INFO, CAPACITY,
    FOOD_PROVIDED, INDOOR, OUTDOOR, FREE_PARKING_AVAILABLE,
    CITY, DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM)
values (
           1, 'De Club', 'https://transitm.mechelen.be/de-club',
           150, false, true, false, true, 'Mechelen', 2);
insert into VENUE (
    ID, VENUE_NAME, LINK_MORE_INFO, CAPACITY,
    FOOD_PROVIDED, INDOOR, OUTDOOR, FREE_PARKING_AVAILABLE,
    CITY, DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM)
values (
           2, 'De Loods', 'https://transitm.mechelen.be/de-loods',
           1000, false, true, false, true, 'Mechelen', 2);
insert into VENUE (
    ID, VENUE_NAME, LINK_MORE_INFO, CAPACITY,
    FOOD_PROVIDED, INDOOR, OUTDOOR, FREE_PARKING_AVAILABLE,
    CITY, DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM)
values (
           3, 'Zapoi', 'https://www.facebook.com/KafeeZapoi/',
           150, true, true, false, false, 'Mechelen', 4);
insert into VENUE (
    ID, VENUE_NAME, LINK_MORE_INFO, CAPACITY,
    FOOD_PROVIDED, INDOOR, OUTDOOR, FREE_PARKING_AVAILABLE,
    CITY, DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM)
values (
           4, 'De Kuub', 'http://www.dekuub.be',
           150, true, true, true, false, 'Mechelen', 4);
insert into VENUE (
    ID, VENUE_NAME, LINK_MORE_INFO, CAPACITY,
    FOOD_PROVIDED, INDOOR, OUTDOOR, FREE_PARKING_AVAILABLE,
    CITY, DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM)
values (
           5, 't''Ile Maline', 'https://www.tilemalines.be',
           400, true, true, true, false, 'Mechelen', 6);
insert into VENUE (
    ID, VENUE_NAME, LINK_MORE_INFO, CAPACITY,
    FOOD_PROVIDED, INDOOR, OUTDOOR, FREE_PARKING_AVAILABLE,
    CITY, DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM)
values (
           6, 'Nekkerhal',
           'https://www.nekkerhalbrusselsnorth.com/nl/organiseer/hal/',
           8800, false, true, false, true, 'Mechelen', 4);




INSERT INTO "PUBLIC"."CLIENT" (ID, NAME, NR_OF_ORDERS, TOTAL_AMOUNT,DISCOUNT_TAKEN)VALUES(1, 'Stijn', 88, 2654, 1);