#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    FILE *fin, *fout;
    fin = fopen("plain_text.txt","r");
    if (fin == NULL)
    {
        puts("The file failed to open.");
        return 0;
    }
    char plain_text[1000];
    int num = 0;

    while(fscanf(fin,"%c",&plain_text[num])!=EOF)
          num ++;
    int length = strlen(plain_text);
    for (num = 0; num < length; num ++)
    {
        plain_text[num] = plain_text[num]-'a';
        plain_text[num] = (plain_text[num] % 28) + 1;
        plain_text[num] = plain_text[num] + 'a';
    }

    fclose(fin);
    fout = fopen("cipher_text.txt","w");
    if (fout == NULL)
    {
        puts("Output file failed.");
        return 0;
    }
    for (num = 0; num < length; num++)
    {
        fprintf(fout,"%c", plain_text[num]);
    }
    fclose(fout);
    puts("Encryption complete.");


    return 0;
}
