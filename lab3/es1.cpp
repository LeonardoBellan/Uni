#include <iostream>

void f(){
  int arr[10];

  int *p = &arr[5];
  for(int i=0; i<10; i++){
    p[i-5] = i;
  }



  for(int i=0;i<10;i++){
    std::cout << arr[i] << ",";
  }
}

void f_illegal(){
  int arr[10];

  //arr[11] = 2;
  arr[2000] = 1;
}


int main(){
  f();
  f_illegal();
  return 0;
}
