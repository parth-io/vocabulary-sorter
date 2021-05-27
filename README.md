## words-and-phrases-sorter

**An experiment in regex to remove duplicate words in a file and transfer any phrase in that file over to a separate file**

Useful for vocabulary lists

Add 'EOF' to the end of a single text document containing all your words and phrases, and run the command `java Solution < Path_to_file.txt`
An easy way to append 'EOF' if you are using bash is to do ` echo 'EOF | tee -a Path_to_file.txt'`


### To do:
Use Google Ngrams Words to arange words by difficulty and complexity
Remove differing word forms (past tense, adjectival form of nouns, etc.) upon user permission