/**
 * receives all inputs from the user, displays all outputs. modifies data for album instances.
 * checks for invalid inputs and displays error messages.
 * 
 * @author Lana Cossettini
 * @version 25/05/2023
 */

import java.util.*;
import java.io.*;
public class SongCollection
{
    private int albumNumber = 0; //number of albums used
    private Album[] album;
    private final int albumMax = 4; //max number of albums
    private void run()
    {
        Scanner console = new Scanner(System.in);
        album = new Album[albumMax]; // creates array
        for (int i = 0; i < albumMax; i++) // initialises array
        {
            album[i] = new Album();
        }
        System.out.println("Would you like to input songs and albums from a file? (Y/N)");
        String selection = console.nextLine();
        if(selection.equalsIgnoreCase("y"))
        {
            external();
            menu();
        }
        else if (selection.equalsIgnoreCase("n"))
        {
            menu();
        }
        else
        {
            System.out.println("Invalid Selection, exiting Program...");
            System.exit(0);
        }
    }
    public static void main(String[] args) 
    {       
       SongCollection sg = new SongCollection();
       sg.run();
    }
    private void menu()
    {
       Scanner console = new Scanner(System.in);
        // displays menu with options for the user to choose from
       System.out.println("Menu");
       System.out.println(" 1. Create Album");
       System.out.println(" 2. Add song to an album");
       System.out.println(" 3. List songs with a specific same");
       System.out.println(" 4. List songs on an album");
       System.out.println(" 5. List all albums and songs");
       System.out.println(" 6. List songs under a certain duration");
       System.out.println(" 7. List songs with a specific genre");
       System.out.println(" 8. Delete album");
       System.out.println(" 9. Delete song");
       System.out.println("10. Exit");
       System.out.println("Enter Selection: ");
       int menuSelection = console.nextInt(); // select from menu
        // switch statement executes case based on user selection
        switch(menuSelection)
        {
            case 1 : createAlbum(); menu(); break;
            case 2 : newSong(); menu(); break;
            case 3 : listSongs(); menu(); break;
            case 4 : listAlbums(); menu(); break;
            case 5 : listAll(); menu(); break;
            case 6 : listLength(); menu(); break;
            case 7 : listGenre(); menu(); break;
            case 8 : deleteAlbum(); menu(); break;
            case 9 : deleteSong(); menu(); break;
            case 10 : System.out.println("Exiting program..."); System.exit(0); break; //exit
            default : System.out.println("Invalid Input, try again"); menu(); 
        }
    }
    public void createAlbum()
    {
        Scanner console = new Scanner (System.in);
        int status = 0;
        if (albumNumber < albumMax) // checks if max number of albums is exceeded
        {
            System.out.println("Enter the album name: ");
            String name = console.nextLine(); // input album name
            for(int i=0; i<albumNumber; i++) //checks if album already exists
            {
                if(name.equalsIgnoreCase(album[i].getName()))
                    status = 1; // status = 1 if album exists
            }
            if (status == 1)
                System.out.println("Album already exists");
            else
            {
                album[albumNumber].setName(name); // sets album name
                System.out.println("Album created Successfully.");
                albumNumber++; // increments number of albums
            }
        }
        else
            System.out.println("Max number of albums reached");
    }
    public void newSong()
    {
        Scanner console = new Scanner (System.in);
        String songs = "";
        System.out.println("Check if song name already exists:");
        String name = console.nextLine(); //name of song
        for (int i=0; i<albumNumber; i++) // creates list of all songs with the name
        {
            songs += album[i].getSpecificSong(name);
        }
        if (songs.equals("")) // if there are no songs with the same name
        {
            System.out.println("There are no songs with this name.");
            System.out.println("Would you like to continue? (Y/N)");
            String choice = console.nextLine(); // yes or no to continue to song creation  
            if (choice.equalsIgnoreCase("y")) // if yes, user can create song
                addSong();
            else if (choice.equalsIgnoreCase("n")) //if no, returns to main menu
                System.out.println("Returning to menu...");
            else
                System.out.println("Invalid Selection.");
        }
        else
        {
            System.out.println("The following songs have this name:");
            System.out.println(songs); // prints list of songs with the same name
            System.out.println("Would you like to continue? (Y/N)");
            String choice = console.nextLine();   
            if (choice.equalsIgnoreCase("y"))
                addSong();
            else if (choice.equalsIgnoreCase("n"))
                System.out.println("Returning to menu...");
            else
                System.out.println("Invalid Selection.");
        }
    }
    public void addSong()
    {
        Scanner console = new Scanner (System.in);
        if (albumNumber != 0) // checks if there are any albums to add a song to
        {
            int status = 0;
            System.out.println("Album Name: ");
            String albumName = console.nextLine(); // enter the name of the album
            for (int i=0; i<albumNumber; i++) // check album exists
            {
                if(album[i].getName().equalsIgnoreCase(albumName))
                    status = 1;
            }
            if (status == 1) // if album exists status = 1
            {
                int albumNum = checkAlbum(albumName); // finds album index
                if (album[albumNum].checkNumber() >= 5) //checks to see if album is full
                {
                    System.out.println("Max songs in this album reached.");
                }
                else
                {
                    System.out.println("Enter song name: ");
                    String name = console.nextLine(); // user inputs song name
                
                    System.out.println("Genres: hip-hop, rock, bossa nova, pop");
                    System.out.println("Enter the genre of the song: ");
                    String genre = console.nextLine().toLowerCase(); // user inputs genre
                    while (!genre.equals("hip-hop") && !genre.equals("rock") && !genre.equals("bossa nova") && !genre.equals("pop")) //checks if input is valid
                    {
                        System.out.println("Invalid Input. Please enter a valid genre from the list.");
                        System.out.println("Genres: hip-hop, rock, bossa nova, pop");
                        System.out.println("Enter the genre of the song: ");
                        genre = console.nextLine().toLowerCase(); // user inputs genre
                    }
                
                    System.out.println("Enter artist name: ");
                    String artist = console.nextLine(); // user inputs artist
                
                    System.out.println("Enter duration in seconds: ");
                    int duration = console.nextInt(); //user inputs duration in seconds
                    while(duration < 0)
                    {
                        System.out.println("Duration cannot be negative. Try again.");
                        System.out.println("Enter duration in seconds: ");
                        duration = console.nextInt(); //user inputs duration in seconds
                    }
                    int durationCheck = album[albumNum].checkDuration(duration); //checks if the duration of the new song will exceed the duration of the album
                
                    if(durationCheck == 1) // duration exceeds album max
                    System.out.println("Song exceeds maximum duration of the album.");
                    else
                    {
                        int songCheck = 0;
                        for (int i=0; i<albumNumber; i++)
                        {
                            songCheck = album[i].checkSong(name, genre, artist, duration); //checks if song already exists, something must be different
                        }
                        if (songCheck == 1) // song already exists
                            System.out.println("Song already exists on an album.");
                        else
                        {
                            album[albumNum].addSong(name, genre, artist, duration); //adds song to album
                            System.out.println("Song successfully added!");
                        }
                    }
                }
            }
            else
                System.out.println("Album does not exist.");
        }
        else 
            System.out.println("No Albums exist, create an album first!");
    }
    public int checkAlbum(String name)
    {
        int index = 0;
        int i;
        for(i=0; i<albumNumber; i++) //finds index of album
        {
            if(name.equalsIgnoreCase(album[i].getName()))
                index = i;
        }
        return index;
    }
    public void listSongs()
    {
        Scanner console = new Scanner (System.in);
        if (albumNumber != 0) //checks if albums exist
            {
                String songName = "";
                System.out.println("Enter song name: ");
                String name = console.nextLine(); //enter name of song
                System.out.println("Name: "+name);
                for (int i=0; i<albumNumber; i++) //checks all albums for songs with inputted name
                {
                    songName += album[i].getSpecificSong(name);
                }
                if (songName.equals("")) // if no songs have the song name
                    System.out.println("there are no songs with this name.");
                else
                    System.out.println(songName); // lists all songs with the song name   
            }
        else
            System.out.println("No songs exist.");
    }
    public void listAlbums()
    {
        Scanner console = new Scanner (System.in);
        int status = 0;
        if(albumNumber != 0) //checks if any albums exist
        {
            System.out.println("Enter album name: ");
            String name = console.nextLine(); // enter name of album
            for (int i=0; i<albumNumber; i++) // checks if the selected album exists
            {
                if (album[i].getName().equalsIgnoreCase(name))
                    status = 1;
            }
            if (status == 1)// if album exists
            {
                int albumNum = checkAlbum(name); // gets index of album
                
                if (album[albumNum].getSongs().equals("")) //if there are no songs in the album
                    System.out.println("There are no songs on this album.");
                else
                {
                    System.out.println("Album: "+album[albumNum].getName()); //prints album name
                    System.out.println(album[albumNum].getSongs()); // prints list of songs in album
                }
            }
            else 
                System.out.println("Album does not exist");
        }
        else
            System.out.println("There are no albums.");
    }
    public void listAll()
    {
        if (albumNumber != 0) // checks if any albums exist
        {
            for (int i=0; i<albumNumber; i++) //creates list of all songs and albums
            {
                if (album[i].getSongs().equals("")) //if there are no songs on an album
                {
                    System.out.println("Album: " + album[i].getName()); // prints album name
                    System.out.println("    There are no songs on this album.");
                }
                else
                {
                    System.out.println("Album: " + album[i].getName()); // prints album name
                    System.out.println(album[i].getSongs()); // lists all songs in the album
                }
            }
        }
        else
            System.out.println("There are no albums or songs.");
    }
    public void listLength()
    {
        Scanner console = new Scanner (System.in);
        String songDuration = "";
        if (albumNumber != 0)
        {
            System.out.println("Enter duration in minutes: ");
            int durationInput = console.nextInt(); // max song duration in minutes
            while (durationInput < 0)
            {
                System.out.println("Duration cannot be negative. Try again.");
                System.out.println("Enter duration in minutes: ");
                durationInput = console.nextInt();
            }
            int duration = durationInput*60; // converts from minutes to seconds
            for (int i=0; i<albumNumber; i++) // checks duration of all songs
            {
                songDuration += album[i].getDurationSong(duration);
            }
            if (songDuration.equals("")) // if there are no songs under the duration
                System.out.println("There are no songs under this duration.");
            else
            {
                System.out.println("songs under "+durationInput+" minutes:"); // duration in minutes
                System.out.println(songDuration); // prints list of songs under the duration
            }
        }
        else
            System.out.println("There are no albums to check songs from.");
    }
    public void listGenre()
    {
        Scanner console = new Scanner (System.in);
        String songGenre = "";
        if (albumNumber != 0)
        {
            System.out.println("Genres: hip hop, rock, bossa nova, pop ");
            System.out.println("Enter the genre of the song: ");
            String genre = console.nextLine().toLowerCase(); // user enter genre
            while (!genre.equals("hip-hop") && !genre.equals("rock") && !genre.equals("bossa nova") && !genre.equals("pop")) //checks if input is valid
                    {
                        System.out.println("Invalid Input. Please enter a valid genre from the list.");
                        System.out.println("Genres: hip-hop, rock, bossa nova, pop");
                        System.out.println("Enter the genre of the song: ");
                        genre = console.nextLine().toLowerCase(); // user inputs genre
                    }
            for (int i=0; i<albumNumber; i++) // gets all songs with selected genre
            {
                songGenre += album[i].getGenreSong(genre);
            }
            if (songGenre.equals("")) //no songs with selected genre
                System.out.println("There are no songs with this genre.");
            else
            {
                System.out.println("songs with the genre "+genre+":");
                System.out.println(songGenre); // list of songs with the selected genre
            }
        }
        else
            System.out.println("There are no albums to check songs from");
    }
    public void deleteAlbum()
    {
        Scanner console = new Scanner(System.in);
        int status = 0;
        if (albumNumber != 0)
        {
            System.out.println("Enter album: ");
            String name = console.nextLine();
            for (int i=0; i<albumNumber; i++) // check album exists
                {
                    if(album[i].getName().equalsIgnoreCase(name))
                        status = 1;
                }
            if (status == 1) // if album exists
            {
                int albumNum = checkAlbum(name); //finds index of selected album
                album[albumNum].setName(""); //deletes album name
                album[albumNum].removeSongs(); //deletes contents of album
                for (int i = albumNum+1; i<albumNumber; i++) //updates index of albums
                {
                    album[i-1] = album[i]; //moves albums after deleted album one to the left
                }
                albumNumber--; // decrements the number of albums
                System.out.println("Album deleted successfully!");
            }
            else
                System.out.println("Album does not exist.");
        }
        else
            System.out.println("No albums exist.");
    }
    public void deleteSong()
    {
        Scanner console = new Scanner(System.in);
        int status = 0;
        if (albumNumber != 0)
        {
            System.out.println("Enter album you wish to delete a song from: ");
            String albumName = console.nextLine(); // enter album to delete song from
            for (int i=0; i<albumNumber; i++) // check album exists
            {
                if(album[i].getName().equalsIgnoreCase(albumName))
                    status = 1;
            }
            if (status == 1) // if album exists
            {
                int albumNum = checkAlbum(albumName); // gets index of album
                
                if (album[albumNum].getSongs().equals("")) // checks if there are songs in the album
                    System.out.println("There are no songs on this album.");
                else
                {
                    System.out.println("Album: "+album[albumNum].getName()); //prints album name
                    System.out.println(album[albumNum].getSongs()); // prints list of songs in album
                    System.out.println("Enter song number: ");
                    int selection = console.nextInt(); // user selects song number

                    int songIndex = selection - 1; // converts selection to array index

                    album[albumNum].removeSong(songIndex); // method that deletes song
                    System.out.println("Song deleted successfully!");
                }
            }
            else
                System.out.println("Album does not exist");
        }
        else
            System.out.println("No albums exist to delete a song from.");
    }
    public void external()
    {
        String fileName = "ReginaCollection.txt"; // name of input file
        //variables for song and album information
        String albumName = "", songName = "", songArtist = "", songDuration = "", songGenre = ""; 
        int duration = 0;
        Scanner inputStream; // scanner object scans file
        try
        {
            inputStream = new Scanner(new File(fileName)); // opens file
            while (inputStream.hasNextLine()) //reads file
            {
                String line = inputStream.nextLine().trim(); // reads the next line and removes white space
                if (line.startsWith("Album")) // if line starts with "Album", album name is found and set
                {
                    albumName = line.substring(line.indexOf(" ")+1); // finds album name
                    album[albumNumber].setName(albumName); // sets album name
                    albumNumber++; // incraments the number of albums
                }
                if(line.startsWith("Name")) // if line starts with "name" finds song name
                {
                    songName = line.substring(line.indexOf(" ")+1);
                }
                if(line.startsWith("Artist")) // if line starts with "artist" finds artist name
                {
                    songArtist = line.substring(line.indexOf(" ")+1);
                }
                if(line.startsWith("Duration")) // if line starts with "duration" finds duration and changes duration to integer
                {
                    songDuration = line.substring(line.indexOf(" ")+1); // finds duration
                    duration = Integer.parseInt(songDuration); // changes duration to an integer
                }
                if (line.startsWith("Genre"))// if line starts with "genre" finds song genre
                {
                    songGenre = line.substring(line.indexOf(" ")+1);
                }
                // if all song information is inputted, adds song
                if(!songName.equals("") && !songArtist.equals("") && duration != 0 && !songGenre.equals(""))
                {
                    int albumNum = albumNumber - 1; // album index
                    album[albumNum].addSong(songName, songGenre, songArtist, duration); // adds song
                    // resets variables for next song
                    albumName = ""; 
                    songName = "";
                    songDuration = "";
                    duration = 0;
                    songGenre = "";
                }
            }
            inputStream.close(); // close file
            System.out.println("Albums and Songs added from external file");
        } catch (FileNotFoundException e) // error if file is not found
        {
            System.out.println("Error, file not found.");
        }
    }
}