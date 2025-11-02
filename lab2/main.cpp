#include <iostream>
#include "Rational.hpp"
int main(){
  Rational frac1;             //default constructor
  Rational frac2(3);            //Integer constructor
  Rational frac3(2,-7);         //Fraction constructor
  Rational frac4,frac5,frac6,frac7,frac8;

  frac4 = frac1 + frac2;        
  frac5 = frac3 + 1;
  frac6 = 1 + frac3;
  frac7 = frac2*frac3;
  frac8 = frac3/frac2;
  
  bool cmp1 = frac5==frac6;
  bool cmp2 = frac1==frac8;
  bool cmp3 = frac1>frac2;
  bool cmp4 = frac8<frac4;


  std::cout << "Fraction 1: " << frac1 << " = " << frac1.to_double() << std::endl;
  std::cout << "Fraction 2: " << frac2 << " = " << frac2.to_double() << std::endl;
  std::cout << "Fraction 3: " << frac3 << " = " << frac3.to_double() << std::endl;
  std::cout << "Fraction 4: " << frac4 << " = " << frac4.to_double() << std::endl;
  std::cout << "Fraction 5: " << frac5 << " = " << frac5.to_double() << std::endl;
  std::cout << "Fraction 6: " << frac6 << " = " << frac6.to_double() << std::endl;
  std::cout << "Fraction 7: " << frac7 << " = " << frac7.to_double() << std::endl;
  std::cout << "Fraction 8: " << frac8 << " = " << frac8.to_double() << std::endl;
  std::cout << "frac5==frac6? " << cmp1 << std::endl;
  std::cout << "frac1==frac8? " << cmp2 << std::endl;
  std::cout << "frac1>frac2? " << cmp3 << std::endl;
  std::cout << "frac8<frac4? " << cmp4 << std::endl;
  
  try{
    frac1 = frac3/frac1;
  }catch(Rational::Invalid){
    std::cout << "Error: cannot divide by 0!" << std::endl;
  }

  return 0;
}
