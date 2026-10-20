#include <iostream>

void print_reference(const int &i, const double &d){
  std::cout << "Integer: " << i << std::endl;
  std::cout << "Double: " << d << std::endl;
}

void print_pointer(int* pi, double* pd){
  std::cout << "Integer pointer: " << *pi << std::endl;
  std::cout << "Double pointer: " << *pd << std::endl;
}
int main(){
  int num = 3;
  double bignum = 3.1415926535;
  print_reference(num,bignum);
  print_pointer(&num, &bignum);
}
