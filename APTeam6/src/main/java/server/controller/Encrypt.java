package server.controller;

import java.util.Scanner;

public class Encrypt {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        String encode = encode(data);
        System.out.println(encode);
        String decode = decode(encode);
        System.out.println(decode);

    }

    public static String encode(String data){

        String result = "";
        char[] chars = data.toCharArray();
        for (char aChar : chars) {
            aChar++;
            result += aChar;
        }


        return result;
    }

    public static String decode(String data){
        String result = "";
        char[] chars = data.toCharArray();
        for (char aChar : chars) {
            aChar--;
            result += aChar;
        }


        return result;
    }

}
