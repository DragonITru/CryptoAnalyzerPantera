package com.javarush.dementev;
import com.javarush.dementev.command.Decode;
import java.io.IOException;



public class Runner {
    public static void main(String[] args) throws IOException {
        Decode decode = new Decode();
        String urlIN = "src\\main\\java\\com\\javarush\\dementev\\testIN.txt";
        String urlOUT = "src\\main\\java\\com\\javarush\\dementev\\testOUT.txt";
        decode.copyWithKey(urlIN,urlOUT,-1);

        }
    }

