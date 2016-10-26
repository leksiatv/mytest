import java.util.ArrayList;
import java.util.List;

class OfferHash {
    private String id;
    private String offerMainHash;
    private List<Picture> pictures = new ArrayList<>();

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    String getOfferMainHash() {
        return offerMainHash;
    }

    void setOfferMainHash(String offerMainHash) {
        this.offerMainHash = offerMainHash;
    }

    List<Picture> getPictures() {
        return pictures;
    }

    void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }


}


