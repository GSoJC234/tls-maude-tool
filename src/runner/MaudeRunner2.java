package runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MaudeRunner2 implements Runnable {

    private int[] requirementInfo;
    private int size;

    public MaudeRunner2(){};

    public MaudeRunner2(byte[] requirementInfo, int size){
        this.requirementInfo = new int[size];
        for(int i = 0; i < size; i++){
            this.requirementInfo[i] = (int)requirementInfo[i];
        }
        this.size = size;
    }

    @Override
    public void run() {
        try {
            Process process = Runtime.getRuntime().exec("maude");
            process.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        String destinationPath = "/home/jaehun/result";
        try {
            File destDir = new File(destinationPath);
            // Check if destination directory exists, create if not
            if (!destDir.exists()) {
                if (destDir.mkdirs()) {
                    System.out.println("Destination directory created.");
                } else {
                    System.out.println("Failed to create destination directory.");
                    return;
                }
            }
            for(int i = 0; i < requirementInfo.length; i++){
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                String formattedTime = now.format(formatter);
                File formalFile = new File("/home/jaehun/git/maude-tls-attacker/resources/ScenarioLog/requirement" +requirementInfo[i] + "_20241121121010.fsc");
                File logFile = new File("/home/jaehun/git/maude-tls-attacker/resources/TestLog/requirement" + requirementInfo[i] + "_20241121121010.rsc");

                String destinationFormalFileName = "requirement" + requirementInfo[i] + "_" +formattedTime+".fsc";
                String destinationLogFileName = "requirement" + requirementInfo[i] + "_"+formattedTime+".rsc";
                // Copy formal file
                try {
                    Path destinationFormalFilePath = Paths.get(destinationPath, destinationFormalFileName);
                    Files.copy(formalFile.toPath(), destinationFormalFilePath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Successfully created: " + destinationFormalFileName);
                } catch (IOException e) {
                    System.out.println("Failed: " + destinationFormalFileName);
                }

                // Copy log file
                try {
                    Path destinationLogFilePath = Paths.get(destinationPath, destinationLogFileName);
                    Files.copy(logFile.toPath(), destinationLogFilePath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Successfully created: " + destinationLogFileName);
                } catch (IOException e) {
                    System.out.println("Failed: " + destinationLogFileName);
                }
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
