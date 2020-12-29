#include <iostream>
#include <cmath>

using namespace std;

struct Chef {        // blueprint
   
   public:
        string name;
        int age;
        float height;
        string preferences;
        string dna;

        Chef() {
            this -> preferences = "Violin \n";
        }

        void show() {
            cout << "My preference is " + this -> preferences;
        }
        void show (string a) {
            cout << "I like " + a;
        }
};

struct ItalianChef : public Chef{      // inheritance
    
    public:
        void show() {
            cout << "I like bass more" << endl;
        }

};

struct Men {
    char name[20];
    int age;
};


int main() {
    
    Chef italianchef;
    italianchef.show();
    italianchef.show("Assmebly");

    int age = 19;
    int *pAge = &age;
    char c = 'a';
    cout << "\n" << *pAge << endl;
    cout << "\n" << *&c << endl;


    Men justin = {
    "Justin",
    21
    };
    cout << justin.age << justin.name<< endl;

}
