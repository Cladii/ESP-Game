package g52010.espgame.model;

import g52010.espgame.dto.ImageDto;
import g52010.espgame.exception.RepositoryException;
import g52010.espgame.repository.ImageRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g52010
 */
public class Images {

    private List<ImageDto> images;

    public Images() {
        images = new ArrayList<>();
    }

    public void initialize() {
        try {
            ImageRepository imageRepo = new ImageRepository();
            images = imageRepo.getAll();
            //Collections.shuffle(images);
        } catch (RepositoryException e) {
            System.out.println("Repository Exception: " + e.getMessage());
        }
    }

    public boolean isEmpty(){
        return images.isEmpty();
    }
    public ImageDto getImage(int index){
        return images.get(index);
    }
    
    public int getSize(){
        return images.size();
    }
}
