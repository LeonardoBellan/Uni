#include <iostream>
int counter = 0;

void call_counter(){
  std::cout << counter++ << std::endl;
}

int main (int argc, char *argv[]) {
  call_counter();
  call_counter();
  call_counter();
  call_counter();
  call_counter();

  return 0;
}
