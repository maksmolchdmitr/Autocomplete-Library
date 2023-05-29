# Autocomplete-Library

## Filter template

### Argumant template
column[<Номер колонки>]<Операция сравнения><Значение>

### Ovarall tremplate
( <Аргумент> & <Аргумент> ) || ( <Аргумент> || <Аргумент> ) ...

## Пример использования
**>**
**Print your filter >** ( ( column[1]>100 & column[1]<6000 ) || ( column[2]<>"Godthaab" & column[1]>50 ) ) && ( column[1]<>300 )
<br> **>**
**Print prefix string >** bo
<br> **>**
...
<br> **>**
4275,"Bowerman Airport","Hoquiam","United States","HQM","KHQM",46.971199035599994,-123.93699646,18,-8,"A","America/Los_Angeles","airport","OurAirports"
<br> **>**
7848,"Bowling Green Warren County Regional Airport","Bowling Green","United States","BWG","KBWG",36.964500427199994,-86.41970062259999,547,-6,"A","America/Chicago","airport","OurAirports"
<br> **>**
3600,"Bowman Field","Louisville","United States","LOU","KLOU",38.2280006409,-85.6636962891,546,-5,"A","America/New_York","airport","OurAirports"
<br> **>**
Количетсво найденных строк 68 Время, затраченное на поиск: 85 мс
<br> **>**
**Print prefix string >** !quit
<br> **>**
Process finished with exit code 0