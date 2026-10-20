#include "Rational.hpp"

//Constructors
Rational::Rational() : num(0), den(1) {}
Rational::Rational(int integer) : num(integer), den(1) {}
Rational::Rational(int num, int den) : num(num), den(den) {
  if(!is_valid()) throw Invalid();
  simplify();
}

//Attributes access
int Rational::numerator() const { return num; };
int Rational::denominator() const { return den; };

//Methods
bool Rational::is_valid() const {
  return den != 0;
}

double Rational::to_double() const{
  return (double)num/(double)den;
}

Rational& Rational::simplify(){
  //mcd between numerator and denominator
  int mcd = std::abs(num);
  int y = std::abs(den);
  while(y>0){
    int r = mcd%y;
    mcd=y;
    y=r;
  }

  //Sign 
  if(den<0) {
    num = -num;
    den = -den;
  }

  num = num/mcd;
  den = den/mcd;

  return *this;
}

//Operator overloading
Rational& Rational::operator=(const Rational& a){
  num = a.numerator();
  den = a.denominator();

  return *this;
}
std::ostream& operator<<(std::ostream& out, Rational& a){
  return out << a.numerator() << "/" << a.denominator();
}

/* Rational Rational::operator+(const Rational& a){
  int denominator = den*a.denominator();
  int numerator = num*a.denominator()+a.numerator()*den;
  return Rational(numerator,denominator).simplify();
 }
 Rational Rational::operator+(int a){
  int numerator = num + a*den;
  int denominator = den;
  return Rational(numerator,denominator);
}*/
Rational operator+(const Rational& a, const Rational& b){
  int denominator = a.denominator()*b.denominator();
  int numerator = a.numerator()*b.denominator() + b.numerator()*a.denominator();
  return Rational(numerator,denominator).simplify();
}
Rational operator+(const Rational& a, int b){
  int numerator = a.numerator() + b*a.denominator();
  int denominator = a.denominator();
  return Rational(numerator, denominator);
}
Rational operator+(int a, const Rational& b){
  int numerator = b.numerator() + a*b.denominator();
  int denominator = b.denominator();
  return Rational(numerator,denominator);
}
Rational operator-(const Rational& a, const Rational& b){
  Rational temp(-b.numerator(),b.denominator());
  return a+temp;
}
Rational operator*(const Rational& a, const Rational& b){
  int numerator = a.numerator()*b.numerator();
  int denominator = a.denominator()*b.denominator();
  return Rational(numerator,denominator).simplify();
}
Rational operator/(const Rational& a, const Rational& b){
  Rational temp(b.denominator(),b.numerator());
  return a*temp;
}
bool operator==(const Rational& a, const Rational& b){
  return a.to_double() == b.to_double();
}
bool operator>(const Rational&a, const Rational& b){
  return a.to_double()>b.to_double();
}
bool operator<(const Rational&a, const Rational& b){
  return a.to_double()<b.to_double();
}
