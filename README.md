# MusicCollection
SENG1110 assignment

When run, the program will display a menu of actions to the user, including one that exits the program. Until the user chooses to exit, the menu is displayed again after each action is completed.

The program should have the following functionalities:
1. Will allow the user to create albums. 
2. Will allow the user to enter a new song into an album. If there are other songs with the same name, the program should list all the details of all the songs with the same name and ask the user if they want to continue. If yes, the user will input album’s name, song details: name, artist, duration, and genre. The program will show an error message if:
the user input a song that already exists (2 songs are identical if name, artist, duration and genre are the same),
OR it is adding a song in an album that exceeds a certain time limit (in song duration times), OR the album does not exist, OR The album is full
3. Will allow the user to request a list of all songs (and the details of each song) with a specific name.
4. Will allow the user to request a list of all songs (and the details of each song) from an album.
5. Will allow the user to request a list of all albums (including all the songs in each album).
6. Will allow the user to request a list of all songs whose duration is under a certain time (in minutes).
7. Will allow the user to request a list of all songs of a specific genre.

The program should show an appropriate message in case the output of functionalities 3, 4, 5, 6, 7 is none.

8. Will allow the user to delete an album. There should be an error message if the album does not exist.
9. Will allow the user to delete a song from an album. There should be an error message if the album does not exist.
10. Will allow the user input albums and songs from an external file. This option will be just available in the beginning.
