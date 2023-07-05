
/**
 * accesses and modifies data for song objects. checks for invalid inputs.
 * returns song data and validity of inputs to songCollection. holds album data.
 *
 * @author Lana Cossettini
 * @version 25/05/2023
 */
public class Album
{
    // instance variables 
    private String name;
    private Song[] song;
    private int songNumber;
    private int totalDuration;
    private final int songMax = 5; // max number of songs
    private final int MAX_TIME = 720; //12 Minutes
    public Album()
    {
        name = "";
        songNumber = 0; // number of songs used
        totalDuration = 0; // total duration of the album
        song = new Song[songMax]; // creates array
        for (int i = 0; i < songMax; i++)  // initialises array
        {
            song[i] = new Song();
        }
    }
    public void setName(String inputName)
    {
        name = inputName;// sets name of album
    }
    public String getName()
    {
        return name; // retrieves name of album
    }
    public void addSong(String name, String genre, String artist, int duration)
    {
        // adds song to album by setting the name, genre, artist and duration
        song[songNumber].setName(name);
        song[songNumber].setGenre(genre);
        song[songNumber].setArtist(artist);
        song[songNumber].setDuration(duration);
        songNumber++;
    }
    public int checkNumber()
    {
        return songNumber; //retrieves number of songs used
    }
    public int checkDuration(int duration) 
    {
        int status = 0;
        for (int i = 0; i < songNumber; i++) // calculates whether song goes over the max duration of the album
        {
            duration += song[i].getDuration();
        }
        if (duration > MAX_TIME)
            status = 1;
        return status;
    }
    public int checkSong(String name, String genre, String artist, int duration)
    {
        int status = 0;
        for (int i=0; i < songNumber; i++) //checks if song already exists
        {
            if (song[i].getName().equalsIgnoreCase(name) && song[i].getGenre().equalsIgnoreCase(genre) && 
                song[i].getArtist().equalsIgnoreCase(artist) && song[i].getDuration() == duration)
                {
                    status = 1;
                }
        }
        return status;
    }
    public String getSpecificSong (String name)
    {
        String songName = "";
        for(int i=0; i<songNumber; i++)// finds songs with the same name as entered song
        {
            if (song[i].getName().equalsIgnoreCase(name))
                songName += song[i].getSongs()+", Album: "+getName()+"\n";
        }
        return songName; 
    }
    public String getDurationSong (int duration)
    {
        String songDuration = "";
        for (int i=0; i<songNumber; i++) //finds songs under a certain duration
        {
            if (song[i].getDuration() <= duration)
                songDuration += song[i].getSongs()+", Album: "+getName()+"\n";
        }
        return songDuration;
    }
    public String getGenreSong (String genre)
    {
        String songGenre = "";
        for (int i=0; i<songNumber; i++) //finds songs with a specific genre
        {
            if(song[i].getGenre().equalsIgnoreCase(genre))
                songGenre += song[i].getSongs()+", Album: "+getName()+"\n";
        }
        return songGenre;
    }
    public String getSongs()
    {
        String songs = "";
        for (int i=0; i<songNumber; i++) // gets information about the songs on an album
        {
            if (song[i].getName().equals(""))
                songs = "";
            else
                songs += i+1+". "+song[i].getSongs()+ "\n";
        }
        return songs;
    }
    public void removeSongs()
    {
        for (int i=0; i<songNumber; i++) // deletes all the songs in the album
        {
            song[songNumber].setName("");
            song[songNumber].setGenre("");
            song[songNumber].setArtist("");
            song[songNumber].setDuration(0);
            songNumber--; // lowers number of songs
        }
    }
    public void removeSong(int songIndex)
    {
        // deletes a specific song on an album
        song[songIndex].setName("");
        song[songIndex].setGenre("");
        song[songIndex].setArtist("");
        song[songIndex].setDuration(0);
        for (int i = songIndex+1; i<songNumber; i++) //updates index of songs
        {
            song[i-1] = song[i]; //moves songs after deleted song one to the left
        }
        songNumber--; //lowers number of songs
    }
}