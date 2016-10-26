import java.util.HashMap;
import java.util.HashSet;

class XmlFile {
    HashMap<String, OfferHash> offersList;
    HashSet<String> pictures;

    XmlFile(HashMap<String, OfferHash> offersList, HashSet<String> pictures) {
        this.offersList = offersList;
        this.pictures = pictures;
    }
}