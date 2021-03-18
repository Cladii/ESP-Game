package g52010.espgame.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Schellekens
 */
public class WordsDto extends Dto<Integer> {

    private ImageDto image;
    private String text;
    private String date;
    final static private DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public WordsDto(Integer key) {
        super(key);
    }

    public WordsDto(Integer key, ImageDto image, String text, String date) {
        super(key);
        this.image = image;
        this.text = text;
        this.date = date;
    }
    
    public WordsDto(Integer key, ImageDto image, String text, LocalDateTime date) {
        super(key);
        this.image = image;
        this.text = text;
        this.date = convert(date);
    }

    public ImageDto getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    private static String convert(LocalDateTime date) {
        return date.format(FORMATTER);
    }
}
