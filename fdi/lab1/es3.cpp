#include <iostream>
#include <string>
#include <vector>

void print(std::string message, std::vector<int> vector){
  std::cout << message << std::endl;
  for(int i=0;i<vector.size();i++)
    std::cout << vector[i] << std::endl;
}

int main (int argc, char *argv[]) {
  std::vector<int> vector = {0,1,2,3,4,5,6,7,8,9};
  print("Message", vector);

  return 0;
}
