import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> myArgs = new ArrayList<>();
        if (args.length == 2) {
            Collections.addAll(myArgs, args);
        } else {
            System.out.println("Help: main.jar Old_XML_file_Path New_XML_file_2_Path");
            System.exit(1);
        }
        long timestamp = System.currentTimeMillis();
        /*+++++++++++++++++++++++++++++++++++++++START++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        List<XmlFile> xmlData = MultiThreadsForParsingFiles.main(myArgs);
        List<CheckedOffers> checkedOffersList = new ArrayList<>();
        List<String> allBrokenImgHashes;
        long timestamped = System.currentTimeMillis();
        System.out.println("Time spent ms on parsing = " + (timestamped - timestamp));
        allBrokenImgHashes = MultiThreads.main(xmlData.get(1).pictures);
        System.out.println("Images checked");
        timestamped = System.currentTimeMillis();
        System.out.println("Time spent ms on check images = " + (timestamped - timestamp));
        for (final String key : xmlData.get(1).offersList.keySet()) {
            CheckedOffers checkedOffer = new CheckedOffers();
            checkedOffer.setId(key);
            checkedOffer.setIsNewOffer(true);
            if (xmlData.get(0).offersList.containsKey(key)) {
                checkedOffer.setIsRmOffer(false);
                checkedOffer.setIsNewOffer(false);
                if (!xmlData.get(1).offersList.get(key).getOfferMainHash().equals(xmlData.get(0).offersList.get(key).getOfferMainHash())) {
                    checkedOffer.setIsModOffer(true);
                }
            }
            for (Picture img : xmlData.get(1).offersList.get(key).getPictures()) {
                allBrokenImgHashes.stream().filter(brkImageHash -> (img.getImg()).toString().equals(brkImageHash)).forEach(brkImageHash -> checkedOffer.setIsBrokenImgOffer(true));
            }
            xmlData.get(0).offersList.remove(key);
            checkedOffersList.add(checkedOffer);
        }
        for (final String key : xmlData.get(0).offersList.keySet()) {
            CheckedOffers checkedOffer = new CheckedOffers();
            checkedOffer.setId(key);
            checkedOffer.setIsRmOffer(true);
            checkedOffersList.add(checkedOffer);
        }
        timestamped = System.currentTimeMillis();
        checkedOffersList.stream().filter(chOffer -> chOffer.getisModOffer() || chOffer.getIsNewOffer() || chOffer.getIsRmOffer() || chOffer.getIsBrokenImgOffer()).forEach(chOffer -> System.out.println(chOffer.toString()));
        /*+++++++++++++++++++++++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        System.out.println("Total time spent ms = " + (timestamped - timestamp));
    }
}
