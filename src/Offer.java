import java.util.ArrayList;
import java.util.List;

class Offer {
    private String id;
    private List hashes = new ArrayList<>();
    private ArrayList<Picture> pictures = new ArrayList<>();

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    void addHash(Integer hash) {
        this.hashes.add(hash);
    }

    ArrayList<Picture> getPictures() {
        return pictures;
    }

    void addPictures(Integer newPicture) {
        Picture img = new Picture();
        img.setImg(newPicture);
        this.pictures.add(img);
    }

    @Override
    public String toString() {
        Integer sum = 0;
        for (Integer hash : (List<Integer>) this.hashes) {
            sum = sum + hash;
        }
        return sum.toString();
    }
}
