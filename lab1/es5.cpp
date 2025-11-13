#include <iostream>
#include <string>
#include <vector>

void print(std::string message, std::vector<int> vector){
  std::cout << message << std::endl;
  for(int i=0;i<vector.size();i++)
    std::cout << vector[i] << std::endl;
}

void fibonacci(int x, int y, std::vector<int> &vector, int n){
  vector.clear();
  vector.push_back(x);
  vector.push_back(y);
  for(int i=1;i<n-1;i++)
    vector.push_back(vector[i]+vector[i-1]);
}


int main (int argc, char *argv[]) {
  std::vector<int> vector = {1,2,4,5,3,3,5};
  fibonacci(0,1,vector,50);
  int i=0;
  while(vector[i]>=0 && i<vector.size())    //L'ultimo elemento della serie prima che si verifichi overflow è l'approsimazione migliore
    i++;
  
  std::cout << "Approssimazione migliore: " << vector[i-1];
  return 0;
}
