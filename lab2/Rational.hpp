#ifndef RATIONAL_H
#define RATIONAL_H

#include <iostream>
#include <cmath>

class Rational {
  public:
    class Invalid {};
    Rational();
    Rational(int integer);
    Rational(int numerator, int denominator);

    int numerator() const;
    int denominator() const;
    double to_double()const;
    Rational& simplify();
    Rational& operator=(const Rational& a);
    //Rational operator+(const Rational& a);
    //Rational operator+(int a);
  private:
    int num {0};
    int den {1};
    bool is_valid() const;
};

std::ostream& operator<<(std::ostream& out, Rational& a);
Rational operator+(const Rational& a, const Rational& b);
Rational operator+(const Rational& a, int b);
Rational operator+(int a, const Rational& b);
Rational operator-(const Rational& a,const Rational& b);
Rational operator*(const Rational& a, const Rational& b);
Rational operator/(const Rational& a, const Rational& b);
bool operator==(const Rational& a, const Rational& b);
bool operator>(const Rational& a, const Rational& b);
bool operator<(const Rational& a, const Rational& b);
#endif
