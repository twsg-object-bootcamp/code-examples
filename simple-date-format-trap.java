import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Test {
    private static SimpleDateFormat isoDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static SimpleDateFormat basicDateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i = 0; i < 60; i++) {
            int loop = i;
            executorService.submit(() -> {
                String dateString = String.format("201909120000%02d", loop);
                System.out.println(String.format("%d. Convert %s to %s", loop, dateString, convert(dateString)));
            });
        }

        executorService.shutdown();
    }

    private static String convert(String dateString) {
        try {
            Date date = basicDateFormatter.parse(dateString);
            return isoDateFormatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}