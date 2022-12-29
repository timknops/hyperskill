# Readability Score
This program allows the user to read the contents of a txt file, and display information about that text. The program will then prompt the user to select
one of the readability calculations to be used on the text, the program will then give the age of understanding associated with the text difficulty.

```
> javac readability/Main.java 
> java readability.Main readability/in.txt 
The text is:
This is the front page of the Simple English Wikipedia. Wikipedias are places where people work together to write encyclopedias in different languages. 
We use Simple English words and grammar here. The Simple English Wikipedia is for everyone! That includes children and adults who are learning English. 
There are 142,262 articles on the Simple English Wikipedia. All of the pages are free to use. 
They have all been published under both the Creative Commons License and the GNU Free Documentation License. 
You can help here! You may change these pages and make new pages. Read the help pages and other good pages to learn how to write pages here. 
If you need help, you may ask questions at Simple talk. Use Basic English vocabulary and shorter sentences. 
This allows people to understand normally complex terms or phrases.

Words: 137
Sentences: 14
Characters: 687
Syllables: 210
Polysyllables: 18
Enter the score you want to calculate (ARI, FK, SMOG, CL, all): all

Automated Readability Index: 7.08 (about 13-year-olds).
Flesch-Kincaid readability tests: 6.31 (about 13-year-olds).
Simple Measure of Gobbledygook: 9.61 (about 15-year-olds).
Coleman-Liau Index: 10.66 (about 16-year-olds).

This text should be understood in average by 14.25-year-olds.
```
