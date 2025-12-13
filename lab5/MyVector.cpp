#include <stdexcept>

class vector{
	int size;
	double* elem;
  int capacity;

	public:
    //Constructors
		vector(int s): size{s}, elem{new double[s]}, capacity{s} {
      if(s==0){
        delete[] elem;
        elem = nullptr;
      }
      else for(int i=0;i<s;i++) elem[i]=0;
    };

    vector(initializer_list<double> lst): size{list.size()},elem{new double[size]},capacity{list.size()} {
      std::copy(lst.begin(),lst.end(),elem);
    }

    vector(const vector& arg): size{arg.size}, elem{new double[size]},capacity{list.size()}{
      std::copy(arg.elem,arg.elem+size,elem);
    }
    vector(vector&& a): size{a.size}, elem{a.elem}, capacity{a.capacity} {
      a.size = 0;
      a.elem = nullptr;
    }
    ~vector(){ delete[] elem; };


    //Methods
		int v_size() const{ return size; };

    vector& operator=(const vector& a){

      double* p = new double[a.size];
      std::copy(a.elem,a.elem+size,p);
      delete[] elem;
      elem = p;

      capacity = a.size;
      size = a.size;
      return *this;
    }
    vector&& operator=(vector&& a){
      delete[] elem;
      elem = a.elem;
      size = a.size;
      capaciy = a.capacity;
      a.elem = nullptr;
      a.size = 0;
      return *this;
    }

    //Access methods
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

    // Modify methods
		void push_back(double d){
      if(elem == nullptr || size>=capacity) reserve(size*2+1);
      elem[size++] = d;
    };
	  void pop_back(){
      if(size>0) size--;
    }
		void reserve(int n) {
      //Guards
      if( n<=capacity ) return;
      if(elem == nullptr){
        elem = new double[n];
        return;
      }
      
      //Create new memory buffer
      capacity=n;
      double* prevVec = elem;
      elem = new double[n];
      std::copy(prevVec,prevVec+size,elem);
      delete[] prevVec;
    };
};

