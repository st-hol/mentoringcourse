package com.epam.exercises.hashcracker.parallelsolution;

import static com.epam.exercises.hashcracker.parallelsolution.Constants.ALL_POSSIBLE_CHARACTERS;
import static com.epam.exercises.hashcracker.parallelsolution.Constants.EQUALS_SIGN;
import static com.epam.exercises.hashcracker.parallelsolution.Constants.FOUND_RESULT_MD5;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Cracker {

    private static final Logger LOG = LoggerFactory.getLogger(MD5Cracker.class);

    private ThreadPoolExecutor executor;
    private String hashMD5 = "69c459dd76c6198f72f0c20ddd3c9447";

    private MD5Cracker(int nThreads) {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
    }

    public static void main(String[] args) {
        MD5Cracker md5Cracker = new MD5Cracker(10);
        LOG.info("start");
        long before = System.currentTimeMillis();
        try {
            md5Cracker.crackPasswordWithBruteForce();
        } catch (RejectedExecutionException | InterruptedException e) {
            LOG.info(String.valueOf(e));
        }
        long after = System.currentTimeMillis();
        long spentMillis = after - before;
        LOG.info("finish");
        LOG.info(String.format("spentMillis = %d", spentMillis));
    }

    private void crackPasswordWithBruteForce() throws InterruptedException {
        AtomicBoolean found = new AtomicBoolean(false);
        HashCrackerService service = new HashCrackerService();
        int max = ALL_POSSIBLE_CHARACTERS.length();
        int[] numberArray = new int[max];
        Arrays.fill(numberArray, -1);

        do {
            executor.execute(() -> {
                Pair<String, String> hashAndValuePair = service.crack(numberArray, max);
                if (checkForMatch(hashAndValuePair.getLeft(), hashMD5)) {
                    executor.shutdownNow();
                    LOG.info("\n\n\n" + FOUND_RESULT_MD5 + hashAndValuePair.getLeft()
                            + EQUALS_SIGN + hashAndValuePair.getRight());
                    found.set(true);
                }
            });
            if (executor.getQueue().size() > 10000) {
                Thread.sleep(10);
            }
        } while (!found.get());
    }

    private boolean checkForMatch(String generatedMD5, String hashMD5) {
        return generatedMD5.equals(hashMD5);
    }
}
