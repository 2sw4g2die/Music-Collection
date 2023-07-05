/**
 * sets song data. returns song data.
 *
 * @author Lana Cossettini
 * @version 25/05/23
 */
public class Song
{
    private String name = "";
    private String artist = "";
    private int duration = 0;
    private String genre = "";
    public void setName(String inputName)
    {
         name = inputName; //sets song
    }
    public String getName()
    {
        return name; // retrieves name
    }
    public void setArtist(String inputArtist)
    {
        artist = inputArtist; // sets artist
    } 
    public String getArtist()
    {
        return artist; // retrieves artist
    }
    public void setDuration(int inputDuration)
    {
        duration = inputDuration; // sets duration
    }
    public int getDuration()
    {
        return duration; // retrieves duration
    }
    public void setGenre(String inputGenre)
    {
        genre = inputGenre; // sets genre
    }
    public String getGenre()
    {
        return genre; // retrieves genre
    }
    public String getSongs()
    {
        return "song: "+name+", genre: "+genre+", artist: "+artist+", duration: "+duration+"s"; // retrieves all song info
    }
}