------------------------------------------------------------------------
This is the project README file. 
------------------------------------------------------------------------

PROJECT TITLE: Hill Cipher
PURPOSE OF PROJECT: Implementation of encryption and decryption for 3x3 Hill Cipher
VERSION or DATE:Jan 2016
AUTHORS: David Chasar


Hill Cipher
CPSC370:  Introduction to Computer Cryptology
Due Wednesday, January 20, 2016 11:59PM

In this programming assignment, you are asked to write C/C++/Java codes to implementencryption/decryption functions for 3x3 Hill Cipher.

1  Details of the program
You need to do the following tasks:
1.  Implement a function that computes the inverse of a 3x3 matrix mod 26 (Hint:  youcan use lookup table to  nd the multiplicative inverse mod 26).
                    x        1   3    5    7   9   11   15    17   19   21   23   25
                    inv(x)   1   9   21   15   3   19    7    23   11    5   17   25
                    
2.  Implement a function that computes the inverse of a 3x3 matrix (K) mod 26.  Specif-ically, you need to compute the determinant and the cofactors for the construction ofK1.

3.  Implement encryption/decryption functions for 3x3 Hill Cipher (Hint: your encryptionfunction should be general enough to hand any length of text.  You should add one ortwoxxif it is not divisible by 3.

4.  Using the following key
                              K=   4   9    15
                                  15   17    6
                                  24    0    17
                                  
             (a)  Invoke your inverse function and print out the inverse of the above keyK(mod26).
             (b)  Invoke your encryption function to print out the cipher text, given the above keyand the plaintext of \paymoremoney".              (c)  Invoke your decryption function to print out the plaintext using the cipher textabove.
             
5.  Using the following key
                              K=    1   2    3
                                    4   5    6
                                    7   8   10
                                    
              (a)  Invoke your inverse function and print out the inverse of the above keyK(mod26).
              (b)  Invoke your encryption function to print out the cipher text, given the above keyand the plaintext of                                    \hillcipherisfuntome".
              (c)  Invoke your decryption function to print out the plaintext using the cipher textabove.
              
