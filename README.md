# java-filmorate

A schematic of the filmorate project database, which allows you to find friends, select popular films, and rate them

[Database schema:](https://dbdiagram.io/d/643d219d6b31947051b777c2)

![image](https://user-images.githubusercontent.com/117895315/233101572-b94af804-45c7-4677-b96f-7fe9b4a4de97.png)

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
***
Get mutual friends:

SELECT u.id, u.name, u.login  
FROM users AS u  
WHERE u.id IN (  
    SELECT f1.friend_id  
    FROM friends f1  
    JOIN friends f2 on f1.friend_id=f2.friend_id   
    WHERE f1.user_id = 1 AND f2.user_id=2  
    AND f1.confirm = 'true' AND f2.confirm = 'true'  
);
