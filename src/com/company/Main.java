package com.company;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.CRC32;

public class Main {

    public static void main(String[] args) throws IOException { // указывается, что возникает исключение
        long hash1 = 0x0B91A851BL; // это CRC32 хэш пароля apple123
        long hash2 = 0x0BA02B6E1L; // хэш CRC32 пароля, который нужно угадать
        CRC32 crc32 = new  CRC32();
        String plaintext = "apple123";
        crc32.update(plaintext.getBytes());
        long checksum = crc32.getValue();
        crc32.reset();
        crc32.update(plaintext.getBytes());
        checksum = crc32.getValue();
        File f = new File("/home/stasy/IdeaProjects/untitled4/src/com/company/10k-most-common.txt");
        Scanner sc = new Scanner(f);
        boolean shouldBreak = false;
        while (sc.hasNextLine()){
            plaintext = sc.nextLine();
            for (int i = 1; i <= 9999; i++){
                crc32.update((plaintext + i).getBytes());
                checksum = crc32.getValue();
                if (checksum == hash2) {
                    System.out.println(plaintext + i);

                    shouldBreak = true;
                    break;
                }
                crc32.reset();
            }
            if (shouldBreak) break;
        }
        sc.close();


    }
}