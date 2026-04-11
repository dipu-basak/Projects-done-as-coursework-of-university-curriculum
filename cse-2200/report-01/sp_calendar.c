#include <stdio.h>

int isLeapYear(int year);
int FirstDayOfYear(int year);


int main() {
    int year, month, day, daysInMonth, weekDay = 0, startingDay;

    printf("Enter Year: ");
    scanf("%d", &year);

    char *months[] = {"January", "February", "March", "April", "May", "June",
                      "July", "August", "September", "October", "November", "December"};

    int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if (isLeapYear(year)) {
        monthDays[1] = 29;
    }

    startingDay = FirstDayOfYear(year);

    printf("\n  ----------- Calendar for %d -----------  \n", year);

    for (month = 0; month < 12; month++) {
        daysInMonth = monthDays[month];
        printf("\n  ------------ %s ------------\n", months[month]);
        printf("  Sun  Mon  Tue  Wed  Thu  Fri  Sat\n");

        // Print blank spaces for the first week
        for (weekDay = 0; weekDay < startingDay; weekDay++) {
            printf("     ");
        }

        for (day = 1; day <= daysInMonth; day++) {
            printf("%5d", day);

            if (++weekDay > 6) {
                weekDay = 0;
                printf("\n");
            }
        }

        if (weekDay != 0) printf("\n");
        // Start of next month
        startingDay = weekDay;
    }

    return 0;
}

// Function to check if a year is a leap year
int isLeapYear(int year) {
    if (year % 400 == 0) return 1;
    if (year % 100 == 0) return 0;
    return (year % 4 == 0);
}

// Function to get the first day of the year (0 = Sun, 1 = Mon, etc.)
// Uses Zeller's-like logic for the Gregorian calendar
int FirstDayOfYear(int year) {
    int day = (year * 365 + ((year - 1) / 4) - ((year - 1) / 100) + ((year - 1) / 400)) % 7;
    return day;
}
