# Genshin Impact Team Composition Application

Term project for CPSC 210 UBC.

## About Genshin Impact

Genshin Impact is an action role-playing game that features an open-world
environment developed by Chinese developer Hoyoverse. It is free-to-play, and is monetized
through an in-game gacha game mechanism that allows for players to obtain new limited characters and weapons
to help them strengthen their team for battle, challenges, and events. Genshin Impact's battle system is mainly
focused on manipulating elemental energy (called "visions") so players will need to rely on elemental reactions to aid their team's
overall damage output.

**The Seven Elements:**
- Anemo (wind) 🌬️
- Cryo (ice) ❄️
- Dendro (nature) 🌱
- Electro (lightning) ⚡
- Geo (earth) ☄️
- Hydro (water) 💧
- Pyro (fire) 🔥

## About this Application

This application is designed to allow users to generate a team composition consisting of 4 characters
from the game, Genshin Impact. The user will have the ability to add up to 4 characters into a team given a list of
characters from the game. Upon completion of adding the characters, users will be able to view
the current party members of their team. If they do not know where to begin, Paimon's team advice corner will provide
common compositions for the character/vision given - these teams are known to include elements that will synergize
well in battle. This application will be extremely useful for players who wish to receive guidance on which characters 
to spend time levelling up in order to maximize their damage output during challenges like the Spiral Abyss. 

I decided to create this project because I
absolutely love and enjoy Genshin Impact, and I think it would be super useful tool to have, especially if you are new
to the game.

## User Stories

- As a user, I want to be able to create a new team and reset it 
- As a user, I want to be able to add characters to the current team
- As a user, I want to be able to view all members of the current team
- As a user, I want to be able to receive advice on what compositions work for each element
- As a user, I want to be able to save the members in my team to file
- As a user, I want to be able to load the members of my team from file

## Phase 4: Task 2

**Representative Sample of Events Logged During App:**

- Character added to team on Wed Mar 30 12:07:07 PDT 2022
- Character added to team on Wed Mar 30 12:07:15 PDT 2022
- Character added to team on Wed Mar 30 12:07:23 PDT 2022
- Character added to team on Wed Mar 30 12:07:32 PDT 2022
- Team was reset on Wed Mar 30 12:07:34 PDT 2022

## Phase 4: Task 3

**Ways I could have improved the project if I had more time:**

- Refactor my GUI class such that it uses the teamWriter and teamReader classes from my persistence package
  - If I had more time, I would have improved the design of GUI to reuse the save and load methods from my UI to 
prevent redundancy.

- Improve the readability of my code in the GUI class by creating more classes (e.g. panel class, button class) to 
support the design of my GUI so that coupling is reduced and the single responsibility principle is maintained.

- Improve the overall design of my project by including design patterns to clean up the overall flow and appearance of my code. 




