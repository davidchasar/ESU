
public class hill
{
    // 3x3 matrix   00 01 02  + - +    determ 00*11*22 + 01*12*20 + 02*10*21 - 02*11*20 - 12*21*00 - 22*01*10
    //              10 11 12  - + -    x    1  3  5  7  9  11  15  17  19  21  23  25
    //              20 21 22  + - +    x-1  1  9 21 15  3  19   7  23  11   5  17  25

    // inverse 3x3 with transpose

    //  m11 11*22 - 12*21       m12 01*22 - 02*21       m13 01*12 - 02*11

    //  m21 10*22 - 12*20       m22 00*22 - 02*20       m23 00*12 - 02*10

    //  m31 10*21 - 11*20       m32 00*21 - 01*20       m33 00*11 - 01*10

    public static final int[][] invTable = {{1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25},
            {1, 9, 21, 15, 3, 19, 7, 23, 11, 5, 17, 25}};

    public static int[][] cofactor(int[][] m) {
        int[][] inverse = new int[3][3];

        inverse[0][0] = ((m[1][1]*m[2][2]) - (m[1][2]*m[2][1])) % 26;
        inverse[0][1] = ((m[0][1]*m[2][2]) - (m[0][2]*m[2][1])) % 26 * -1;
        inverse[0][2] = ((m[0][1]*m[1][2]) - (m[0][2]*m[1][1])) % 26;
        inverse[1][0] = ((m[1][0]*m[2][2]) - (m[1][2]*m[2][0])) % 26 * -1;
        inverse[1][1] = ((m[0][0]*m[2][2]) - (m[0][2]*m[2][0])) % 26;
        inverse[1][2] = ((m[0][0]*m[1][2]) - (m[0][2]*m[1][0])) % 26 * -1;
        inverse[2][0] = ((m[1][0]*m[2][1]) - (m[1][1]*m[2][0])) % 26;
        inverse[2][1] = ((m[0][0]*m[2][1]) - (m[0][1]*m[2][0])) % 26 * -1;
        inverse[2][2] = ((m[0][0]*m[1][1]) - (m[0][1]*m[1][0])) % 26;

        // change negative numbers back to positive
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (inverse[i][j] < 0) {
                    inverse [i][j] += 26;
                }
            }
        }
        return inverse;
    }

    public static int determanant(int[][] dm) {
        // determ 00*11*22 + 01*12*20 + 02*10*21 - 02*11*20 - 12*21*00 - 22*01*10
        int det = ((dm[0][0]*dm[1][1]*dm[2][2]) + (dm[0][1]*dm[1][2]*dm[2][0]) + (dm[0][2]*dm[1][0]*dm[2][1]) - 
                (dm[0][2]*dm[1][1]*dm[2][0]) - (dm[1][2]*dm[2][1]*dm[0][0]) - (dm[2][2]*dm[0][1]*dm[1][0])) % 26;

        // if det is negative change to positive
        if (det < 0){
            det += 26;
        }        

        // invTable
        //  x    1  3  5  7  9  11  15  17  19  21  23  25
        //  x-1  1  9 21 15  3  19   7  23  11   5  17  25
        int num = 0;

        // swap det with inverse det
        for (int i = 0; i < 12; i++) {
            if (det == invTable[0][i]){
                num = invTable[1][i];
            }
        }

        //System.out.println("the determanant is: " + det);
        //System.out.println("the inverse determanant is: " + num);
        //System.out.println();
        return num;
    }

    public static String encrypt(int[][] key, String plainText) {
        plainText = plainText.toLowerCase();
        String cipherText = "";

        // If not divisible by 3, add some x
        if ((plainText.length() % 3) > 0) {
            for (int i = (3 - (plainText.length() % 3)); i > 0; i--) {
                plainText += "x";
            }
        }

        for (int i = 0; i < plainText.length(); i += 3) {
            int p1 = plainText.charAt(i) - 97;
            int p2 = plainText.charAt(i + 1) - 97;
            int p3 = plainText.charAt(i + 2) - 97;

            int c1 = (((p1*key[0][0]) + (p2*key[0][1]) + (p3*key[0][2])) %26) + 97;
            int c2 = (((p1*key[1][0]) + (p2*key[1][1]) + (p3*key[1][2])) %26) + 97;
            int c3 = (((p1*key[2][0]) + (p2*key[2][1]) + (p3*key[2][2])) %26) + 97;

            char[] add = {(char) c1, (char) c2,(char) c3};

            for (int j = 0; j < 3; j++) {
                cipherText += add[j]; 

            }

        }
        System.out.println(cipherText);

        return cipherText;
    }

    public static int[][]inverseKey (int[][] key) {
        int[][] inverseKey = new int [3][3];
        int[][] transpose = cofactor(key); 
        int det = determanant(key);
        // multiply the inverse det by the cofactor
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverseKey[i][j] = transpose[i][j]* det % 26;
                if(inverseKey[i][j] < 0){
                    inverseKey[i][j] += 26;
                }
            }
        }
        //System.out.println("The inverse key is: ");

        //printArray(inverseKey);
        return inverseKey;
    }

    public static String decrypt(int[][] key, String cipherText) {
        cipherText = cipherText.toLowerCase();
        String plainText = "";
        int[][] invKey = inverseKey(key);

        for (int i = 0; i < cipherText.length(); i += 3) {
            int c1 = cipherText.charAt(i) - 97;
            int c2 = cipherText.charAt(i + 1) - 97;
            int c3 = cipherText.charAt(i + 2) - 97;

            int p1 = (((c1*invKey[0][0]) + (c2*invKey[0][1]) + (c3*invKey[0][2])) %26) + 97;
            int p2 = (((c1*invKey[1][0]) + (c2*invKey[1][1]) + (c3*invKey[1][2])) %26) + 97;
            int p3 = (((c1*invKey[2][0]) + (c2*invKey[2][1]) + (c3*invKey[2][2])) %26) + 97;

            char[] add = {(char) p1, (char) p2,(char) p3};

            for (int j = 0; j < 3; j++) {
                plainText += add[j]; 

            }

        }
        System.out.println(plainText);

        return plainText;
    }

    public static void printArray(int[][]fm) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(fm[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static void main (String [] args) {
        // keys
        int[][] key = {{4, 9, 15},
                {15, 17, 6},
                {24, 0, 17}};

        int[][] key2 = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 10}};

        // key 1
        System.out.println("The key");
        printArray(key);
        System.out.println();

        System.out.println("The inverse key");
        int [][]ik = inverseKey(key);
        printArray(ik);

        System.out.println();
        System.out.println("The encrypted text is: ");
        encrypt(key, "paymoremoney");
        System.out.println();

        System.out.println("The decrypted text is: ");
        decrypt(key, "efonafwkwgrs");
        System.out.println();
        System.out.println();

        // key 2   
        System.out.println("The 2nd key");
        printArray(key2);
        System.out.println();

        System.out.println("The inverse 2nd key");
        int [][]ik2 = inverseKey(key2);
        printArray(ik2);

        System.out.println();
        System.out.println("The encrypted text is: ");
        encrypt(key2, "hillcipherisfuntome");
        System.out.println();

        System.out.println("The decrypted text is: ");
        decrypt(key2, "eepnyrpptjizgqnfkbpja");

    }
}
