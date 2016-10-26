import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MultiThreadsForParsingFiles implements Callable<XmlFile> {
    private String fileName;
    private Boolean isSecondFile;

    private MultiThreadsForParsingFiles(String fileName, Boolean numFile) {
        this.fileName = fileName;
        this.isSecondFile = numFile;
    }

    static List<XmlFile> main(List<String> arg0) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<XmlFile>> list = new ArrayList<>();
        List<XmlFile> offerListFiles = new ArrayList<>();
        for (int i = 0; i < arg0.size(); i++) {
            Boolean isSecFile = false;
            if (i == 1) {
                isSecFile = true;
            }
            Callable<XmlFile> callable = new MultiThreadsForParsingFiles(arg0.get(i), isSecFile);
            Future<XmlFile> future = executor.submit(callable);
            list.add(future);
        }
        for (Future<XmlFile> fut : list) {
            try {
                if (fut.get() != null) {
                    offerListFiles.add(fut.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return offerListFiles;

    }

    @Override
    public XmlFile call() throws Exception {
        XmlFile offerListFile;
        offerListFile = ParseXml.parseXml(fileName, isSecondFile);
        return offerListFile;
    }
}

