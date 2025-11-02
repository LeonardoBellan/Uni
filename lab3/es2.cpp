#include <iostream>

void print_array_length(double arr[]){
  std::cout << "Funzione: " << sizeof(arr) << std::endl;
  std::cout << arr[3];
}
int main(){
  double arr[] = {1,2,3,4,5,6,7,8,9,10,24};
  std::cout << sizeof(arr) << std::endl;
  print_array_length(arr);
  return 0;
}
