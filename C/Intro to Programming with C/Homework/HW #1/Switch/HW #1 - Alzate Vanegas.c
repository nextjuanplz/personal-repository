//HW #1
//Purpose: to create a program that can give you some information about planets
#include <stdio.h>
#include <stdlib.h>

//Constants to be used
#define PI 3.14159265359
#define con 1/PI
#define con_2 0.621371192 /*con_2 = km to mi conversion*/
#define con_3 10*10*10*10*10*10 /*10^6*/

//Circumferences (c = diameter * pi * conversion_to_km)
#define c_m 4879*PI*con_2
#define c_v 12104*PI*con_2
#define c_e 12756*PI*con_2
#define c_a 6792*PI*con_2
#define c_j 142984*PI*con_2
#define c_s 120536*PI*con_2
#define c_u 51118*PI*con_2
#define c_n 49528*PI*con_2
#define c_p 2370*PI*con_2

//Distances from sun (d = distance * 10^6 * conversion_to_km)
#define d_m 57.9*con_3*con_2
#define d_v 108.2*con_3*con_2
#define d_e 149.6*con_3*con_2
#define d_a 227.9*con_3*con_2
#define d_j 778.6*con_3*con_2
#define d_s 1433.5*con_3*con_2
#define d_u 2872.5*con_3*con_2
#define d_n 4495.1*con_3*con_2
#define d_p 5906.4*con_3*con_2

//Source code
int main()
{
//Introduction
    printf("Welcome to the program \"Learning about Planets\"!\n\n");
    printf("Please type in the corresponding key and hit enter to learn about the following planets:\n\n");

//Planet options for scanf()
    printf("m = Mercury\n");
    printf("v = Venus\n");
    printf("e = Earth\n");
    printf("a = Mars\n");
    printf("j = Jupiter\n");
    printf("u = Uranus\n");
    printf("n = Neptune\n");
    printf("p = Pluto\n\n");

//Checking for planet name
    char planet_name;
    scanf("%c",&planet_name);

//Declaring surface area (sa = (con * con)/PI) and distance from the sun (d)
    float sa, d;

//Print-out in response to scanf()
    switch (planet_name)
    {
        case 'm': /*Mercury*/
            sa = c_m * c_m * con;
            d = d_m;
            printf("\nYou have selected to learn about Mercury.\n\n");
            printf("The surface area of Mercury is %f square miles.\n",sa);
            printf("Mercury is %f miles away from the Sun.\n\n",d);
            break;

        case 'v': /*Venus*/
            sa = c_v * c_v * con;
            d = d_v;
            printf("\nYou have selected to learn about Venus.\n\n");
            printf("The surface area of Venus is %f square miles.\n",sa);
            printf("Venus is %f miles away from the Sun.\n\n",d);
            break;

        case 'e': /*Earth*/
            sa = c_e * c_e * con;
            d = d_e;
            printf("\nYou have selected to learn about Earth.\n\n");
            printf("The surface area of Earth is %f square miles.\n",sa);
            printf("Earth is %f miles away from the Sun.\n\n",d);
            break;

        case 'a': /*Mars*/
            sa = c_a * c_a * con;
            d = d_a;
            printf("\nYou have selected to learn about Mars.\n\n");
            printf("The surface area of Mars is %f square miles.\n",sa);
            printf("Mars is %f miles away from the Sun.\n\n",d);
            break;

        case 'j': /*Jupiter*/
            sa = c_j * c_j * con;
            d = d_j;
            printf("\nYou have selected to learn about Jupiter.\n\n");
            printf("The surface area of Jupiter is %f square miles.\n",sa);
            printf("Jupiter is %f miles away from the Sun.\n\n",d);
            break;

        case 's': /*Saturn*/
            sa = c_s * c_s * con;
            d = d_s;
            printf("\nYou have selected to learn about Saturn.\n\n");
            printf("The surface area of Saturn is %f square miles.\n",sa);
            printf("Saturn is %f miles away from the Sun.\n\n",d);
            break;

        case 'u': /*Uranus*/
            sa = c_u * c_u * con;
            d = d_u;
            printf("\nYou have selected to learn about Uranus.\n\n");
            printf("Uranus is huuuge!\n\n");
            printf("Its surface area is %f square miles.\n",sa);
            printf("Also, Uranus is %f miles away from the Sun.\n\n",d);
            break;

        case 'n': /*Neptune*/
            sa = c_n * c_n * con;
            d = d_n;
            printf("\nYou have selected to learn about Neptune.\n\n");
            printf("The surface area of Neptune is %f square miles.\n",sa);
            printf("Neptune is %f miles away from the Sun.\n\n",d);
            break;

        case 'p': /*Pluto*/
            sa = c_p * c_p * con;
            d = d_p;
            printf("\nYou have selected to learn about Pluto.\n\n");
            printf("The surface area of Pluto is %f square miles.\n",sa);
            printf("Pluto is %f miles away from the Sun.\n\n",d);
            printf("Pluto is not a planet. It's a dwarf planet!\n\n");
            printf("Don't forget it!\n\n");
            break;

        default: /*Anything else*/
            printf("\nYou are stupid.\n\n");
            printf("You will never learn anything.\n\n");
    }
    return (0);
}
