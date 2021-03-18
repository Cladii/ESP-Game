package g52010.espgame.dto;

/**
 *
 * @author g52010
 */
public class ImageDto extends Dto<String> {

    public ImageDto(String path) {
        super(path);
    }

    @Override
    public String getKey() {
        return key;
    }
}
