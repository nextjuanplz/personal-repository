#include <stdio.h>

int main()
{
	char password[] = "fuzzy\"\n\t wuzzy";

	printf("The password is \"%s\"\n",password);

	return(0);
}

