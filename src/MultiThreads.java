import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

class MultiThreads implements Callable<String> {
    private String image;


    private MultiThreads(String image) {
        this.image = image;
    }

    static List<String> main(HashSet<String> arg0) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(16);
        List<Future<String>> list = new ArrayList<>();
        List<String> listBrokenImgHashes = new ArrayList<>();

        for (String image : arg0) {
            Callable<String> callable = new MultiThreads(image);
            Future<String> future = executor.submit(callable);
            list.add(future);
        }

        for (Future<String> fut : list) {
            try {

                if (fut.get() != null) {
                    listBrokenImgHashes.add(fut.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return listBrokenImgHashes;

    }

    @Override
    public String call() throws Exception {

        if (!Check_image.checkIMG(image)) {
            Integer brokenImgHash = MakeHash.main(image);
            return brokenImgHash.toString();
        }
        return null;
    }

}

