# java-filmorate

A schematic of the filmorate project database, which allows you to find friends, select popular films, and rate them

[Database schema:](https://dbdiagram.io/d/643d219d6b31947051b777c2)

![image](https://user-images.githubusercontent.com/117895315/232494537-fa823ce6-4221-477d-a22c-b4114aeae17d.png)

## Query examples

Get a list of the user's subscribers::  
  
 SELECT *  
 FROM friend  
 WHERE adresser_id = 1  
***
Get a list of user subscriptions: 
  
 SELECT *  
 FROM friend  
 WHERE requester_id = 1  
***
Find out all film genres:
  
SELECT f.name, g.name  
FROM film as f  
JOIN film_genre as fg on f.id = fg.film_id  
JOIN genre as g on fg.genre_id = g.id  
 ***
 Find out who liked the film:  
 
SELECT f.name, u.name  
FROM film as f  
JOIN like_info as li on f.id = li.film_id  
JOIN "user" as u on u.id = li.user_id  
***
Sort films by popularity:  
  
SELECT f.name,  
COUNT (li.film_id) as movie_likes  
FROM film as f  
JOIN like_info as li on f.id = li.film_id  
GROUP BY f.name  
ORDER BY movie_likes DESC  
