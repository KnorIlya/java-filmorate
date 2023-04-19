# java-filmorate

A schematic of the filmorate project database, which allows you to find friends, select popular films, and rate them

[Database schema:](https://dbdiagram.io/d/643d219d6b31947051b777c2)

![image](https://user-images.githubusercontent.com/117895315/233055006-dbce1e31-e4d9-434a-9471-ab2994e4c49f.png)

## Query examples

Get a list of the user's subscribers::  
  
SELECT u.name  
FROM users as u  
JOIN friends as f on f.adresser_id = u.id   
***
Get a list of user subscriptions: 
  
SELECT u.name  
FROM users as u  
JOIN friends as f on f.requester_id = u.id    
***
Find all the user's friends
  
SELECT u.name  
FROM users as u  
JOIN friends as f on f.adresser_id = u.id   
WHERE f.confirm = true  

***
Find out all film genres:
  
SELECT f.name, g.name  
FROM films as f  
JOIN film_genres as fg on f.id = fg.film_id  
JOIN genres as g on fg.genre_id = g.id  
 ***
 Find out who liked the film:  
 
SELECT f.name, u.name  
FROM films as f  
JOIN like_info as li on f.id = li.film_id  
JOIN users as u on u.id = li.user_id  
***
Sort films by popularity:  
  
SELECT f.name,  
COUNT (li.user_id) as movie_likes  
FROM films as f  
JOIN like_info as li on f.id = li.film_id  
GROUP BY f.name  
ORDER BY movie_likes DESC
