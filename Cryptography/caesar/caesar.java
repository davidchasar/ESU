
public class caesar
{

    public static String encode(String message, int key)
    {
        key = key % 26;
        //message=message.toLowerCase();
        StringBuilder encoded = new StringBuilder();

        for (char i : message.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + key) % 26 ));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + key) % 26 ));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }

    public static String decode(String message, int key)
    {
        return encode(message, 26 - key);
    }

    public static void brute(String message, int key, String keyword) 
    {
        message = message.toLowerCase();
        keyword = keyword.toLowerCase();
        if (key == 26) {
            return;
        } else {
            String plainText = decode(message, key);
            // checks for keyword in decoded plain text. 
            if (keyword != "") {
                if(plainText.matches(".*" + keyword + ".*")) {
                    if (key < 10) {
                        System.out.println("Key = " + key + ": " + plainText);
                    } else {
                        System.out.println("Key = " + key + ": " + plainText);
                    }
                }
            // if the keyword is not known
            } else {
                if (key < 10) {
                    System.out.println("Key = " + key + ":  " + plainText);
                } else {
                    System.out.println("Key = " + key + ":  " + plainText);
                }
            }
    
        }
        caesar.brute(message, key + 1, keyword);
    }   
    
    
    public static void main(String[] args) 
    {
        String str1 = "Get me a vanilla ice cream, make it a double.";
        System.out.println(str1);
        System.out.println(caesar.encode(str1, 6));
        System.out.println();

        String str2 = "I don't much care for Leonard Cohen.";
        System.out.println(str2);
        System.out.println(caesar.encode(str2, 15));
        System.out.println();

        String str3 = "I like root beer floats.";
        System.out.println(str3);
        System.out.println(caesar.encode(str3, 16));
        System.out.println();

        String str4 = "NDUZS FTQ BUZQ OAZQE.";
        System.out.println(str4);
        System.out.println(caesar.decode(str4, 12));
        System.out.println();

        String str5 = "FDHVDU QHHGV WR ORVH ZHLJKW.";
        System.out.println(str5);
        System.out.println(caesar.decode(str5, 3));
        System.out.println();

        String str6 = "UFGIHXM ULY NUMNYS.";
        System.out.println(str6);
        System.out.println(caesar.decode(str6, 20));
        System.out.println();

        
        //key = "chapel";
        System.out.println("Keyword:  chapel\nCipher:   GRYY GURZ GB TB GB NZOEBFR PUNCRY.");
        caesar.brute("GRYY GURZ GB TB GB NZOEBFR PUNCRY.", 1, "chapel");
        System.out.println();

        //key = "cymbal";
        System.out.println("Keyword:  cymbal\nCipher:   WZIV KYV JYFK NYVE KYV TPDSRCJ TIRJY.");
        caesar.brute("WZIV KYV JYFK NYVE KYV TPDSRCJ TIRJY.", 1, "cymbal");
        System.out.println();

        //key = "";
        System.out.println("Keyword: \nCipher:   BAEEQ KLWOSJL OSK S ESF OZG CFWO LGG EMUZ.");
        caesar.brute("BAEEQ KLWOSJL OSK S ESF OZG CFWO LGG EMUZ.", 1, "");
        System.out.println();
        





    }
}
