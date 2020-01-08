package com.company;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {




        protected static String AES_256 (String Data) throws Exception
        {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);

            // Generate Key
            SecretKey key = keyGenerator.generateKey();

            //Generating IV.
            byte[] IV = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(IV);

            byte[] cipherText = encrypt(Data.getBytes(),key, IV);
            String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
            System.out.println("Generated Master Key: " + encodedKey);



            return (Base64.getEncoder().encodeToString(cipherText));

            //String decryptedText = decrypt(cipherText,key, IV);
            //System.out.println("DeCrypted Text : "+decryptedText);

        }

        private static byte[] encrypt (byte[] plaintext,SecretKey key,byte[] IV ) throws Exception
        {
            //Get Cipher Instance
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //Create SecretKeySpec
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

            //Create IvParameterSpec
            IvParameterSpec ivSpec = new IvParameterSpec(IV);

            //Initialize Cipher for ENCRYPT_MODE
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            //Perform Encryption
            byte[] cipherText = cipher.doFinal(plaintext);

            return cipherText;
        }

        protected static String decrypt (byte[] cipherText, SecretKey key,byte[] IV) throws Exception
        {
            //Get Cipher Instance
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //Create SecretKeySpec
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

            //Create IvParameterSpec
            IvParameterSpec ivSpec = new IvParameterSpec(IV);

            //Initialize Cipher for DECRYPT_MODE
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            //Perform Decryption
            byte[] decryptedText = cipher.doFinal(cipherText);

            return new String(decryptedText);
        }

    }


