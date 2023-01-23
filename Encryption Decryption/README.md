# Encryption Decryption

This program allows the user to encrypt/decrypt messages. 

*Must be run from the command line*

Arguments:  
```-mode``` enc/dec  
```-alg``` shift/unicode  
```-data``` string of text  
```-out``` path of file to write to  
```-in``` path of file to read from  
```-key``` number used in the encryption/decryption process  
  
  ```
> javac encryptdecrypt/*.java                                                       
> java encryptdecrypt.Main -mode enc -alg unicode -data "This is a test" -key 81
FZ[eq[eqSqfWef
> java encryptdecrypt.Main -mode dec -alg unicode -data "FZ[eq[eqSqfWef" -key 81
This is a test
> java encryptdecrypt.Main -in read_from -out write_to -alg unicode -key 5
  ```
