#include <iostream>
#include "MyVector.cpp"

int main(){
  vector myVec(0);
  myVec.reserve(10);  
  std::cout << myVec.v_size() << ", " << myVec[0];
}
