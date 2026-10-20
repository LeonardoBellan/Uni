#include <stdexcept>

class vector{
	int size;
	double* elem;
  int capacity;

	public:
		vector(int s): size{s}, elem{new double[s]}, capacity{s} {
      if(s==0){
        delete[] elem;
        elem = nullptr;
      }
      else for(int i=0;i<s;i++) elem[i]=0;
    };
    
    ~vector(){ delete[] elem; };

		int v_size() const{ return size; };

		const double& operator[](int i) const{
      return elem[i];
    };
		double& operator[](int i){
      return elem[i];
    };

		const double& at(int i) const{
      if(i<0 || i>= size) throw std::out_of_range("Array index out of bounds");
      return elem[i];
    };
		double& at(int i){
      if(i<0 || i>= size) throw std::out_of_range("Array index out of bounds");
      return elem[i];
    };
		void push_back(double d){
      if(elem == nullptr || size>=capacity) reserve(size*2+1);
      elem[size++] = d;
    };
	  void pop_back(){
      if(size>0) size--;
    }
		void reserve(int n) {
      if( n<=capacity ) return;
      if(elem == nullptr){
        elem = new double[n];
        return;
      }

      //Create new memory buffer
      capacity=n;
      double* prevVec = elem;
      elem = new double[n];
      //Reallocate elements of previous buffer
      for(int i=0;i<size;i++){
        elem[i] = prevVec[i];
      }
      delete[] prevVec;
    };
};

