# Live football world cup scoreboard

## Overview
The following repo contains Live football world cup scoreboard which is Java based application.

## Task requirements:
You are working in a sports data company, and we would like you to develop a new Live Football
World Cup Scoreboard library (or frontend application) that shows all the ongoing matches and their
scores.
The scoreboard supports the following operations:
1. Start a new game, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:
   a. Home team
   b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
   team score.
3. Finish game currently in progress. This removes a match from the scoreboard.
4. Get a summary of games in progress ordered by their total score. The games with the same
   total score will be returned ordered by the most recently started match in the scoreboard.
   For example, if following matches are started in the specified order and their scores
   respectively updated:
   a. Mexico 0 - Canada 5
   b. Spain 10 - Brazil 2
   c. Germany 2 - France 2
   d. Uruguay 6 - Italy 6
   e. Argentina 3 - Australia 1
   The summary should be as follows:
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2

## Guidelines
1. Clone this repository

2. Go to src/test/java directory and run WorldCupWorldCupScoreBoardTest.java

## Junit tests

All Junit tests are in Java test class WorldCupWorldCupScoreBoardTest.java 

WorldCupWorldCupScoreBoardTest contains Junit test :
```java
	void itShouldInsertMatch()
```
```java
	void itShouldNotInsertMatchWhenSameMatchExists()
```
```java
	void itShouldNotInsertMatchWhenActiveMatchExist()
```
```java
	void itShouldRemoveMatch()
```
```java
	void itShouldUpdateMatch()
```
```java
	void itShouldNotUpdateMatchWhenMatchIsDisabled()
```
```java
	void itShouldNotUpdateTeamWithNegativeTeamScoreValue()
```
```java
	void itShouldReturnOrderedMatches()
```

## Support
Any question regarding this repo can be send to email balaztomas0@gmail.com